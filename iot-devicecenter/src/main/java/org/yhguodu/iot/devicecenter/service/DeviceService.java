package org.yhguodu.iot.devicecenter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.yhguodu.iot.common.device.DeviceContext;
import org.yhguodu.iot.common.device.DeviceServiceStatus;
import org.yhguodu.iot.common.exception.ExceptionMeta;
import org.yhguodu.iot.common.exception.IotException;
import org.yhguodu.iot.common.message.*;
import org.yhguodu.iot.common.result.IotAckResult;
import org.yhguodu.iot.common.result.ResultGenerator;
import org.yhguodu.iot.devicecenter.config.DeviceCenterProperties;
import org.yhguodu.iot.devicecenter.netty.NettyEvent;
import org.yhguodu.iot.devicecenter.netty.NettyServer;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/10/14.
 */

@Service
public class DeviceService implements SmartLifecycle,EventHandler<NettyEvent> {
    private static final Logger logger = LoggerFactory.getLogger(DeviceService.class);
    @Autowired
    private DeviceCenterProperties properties;

    private NettyServer nettyServer;
    private ConcurrentHashMap<Integer,DeviceContext> devicesMap = new ConcurrentHashMap<Integer, DeviceContext>();
    private ConcurrentHashMap<Long,NettyEvent> nettyEvents = new ConcurrentHashMap<>();
    private volatile boolean isRunning;

    private ExecutorService nettyThread;

    public void start() {
        isRunning = true;
        nettyServer = new NettyServer(properties.getPort(),this);
        logger.info("nett port {}",properties.getPort());
        nettyThread = Executors.newSingleThreadExecutor();
        nettyThread.submit(()->nettyServer.run());
    }

    public IotAckResult cmdDevice(int deviceId, String cmd) {
        if(!devicesMap.containsKey(deviceId))
            return ResultGenerator.genFailure(new IotException(ExceptionMeta.DeviceNotFound));

        DeviceContext context = devicesMap.get(deviceId);
        if(context.getServiceStatus() != DeviceServiceStatus.Up)
            return ResultGenerator.genFailure(new IotException(ExceptionMeta.DeviceNotActive));
        try {
            Future<IotMessage> result = writeCmdToDevice(deviceId, cmd);
            IotMessage msg = result.get(2, TimeUnit.SECONDS);
            return ResultGenerator.genSuccess(msg);
        }
        catch(IotException e) {
            logger.error("IOT Exception",e);
            return ResultGenerator.genFailure(e);
        }
        catch(ExecutionException e) {
            logger.error("ExecutionExceptin",e);
            return ResultGenerator.genFailure(new IotException(ExceptionMeta.CommandSendingError));
        }
        catch(TimeoutException|InterruptedException e) {
            logger.info("Timeout Exception",e);
            return ResultGenerator.genFailure(new IotException(ExceptionMeta.CommandRspNotReceived));
        }
    }

    @Async
    public Future<IotMessage> writeCmdToDevice(int deviceId,String cmd) throws IotException{
        logger.info("Send Cmd {} To Device {}",cmd,deviceId);
        CmdCallBack callBack = new CmdCallBack(properties.getCmdTimeout());
        IotMessage command = new IotCmdReq(deviceId,cmd);
        NettyEvent event = new NettyEvent(command,null,callBack);
        nettyEvents.put(command.getMsgId(),event);
        writeEvent(event);
        IotMessage response = callBack.getResponse();
        nettyEvents.remove(command.getMsgId());
        return new AsyncResult<>(response);
    }

    public boolean isAutoStartup() {
        return true;
    }

    public void stop(Runnable runnable) {
    }

    public void stop() {
        isRunning = false;
    }

    public boolean isRunning() {
        return false;
    }
    public int getPhase() {
        return 0;
    }

    @Override
    public void readEvent(NettyEvent event) {
        IotMessage msg = event.getIotMessage();
        switch(msg.getMsgType()) {
            case ATTACHING:
                AttachingMessage attach = (AttachingMessage) msg;
                DeviceContext context = new DeviceContext(attach.getDevice());
                context.setServiceStatus(DeviceServiceStatus.Up);
                devicesMap.put(msg.getDeviceId(),context);
                nettyServer.addDeviceChannelPair(msg.getDeviceId(),event.getChannel());
                break;
            case KEEPALIVE:
                break;
            case DATA:
                IotDataMessage data = (IotDataMessage) msg;
                logger.info("receive data {} from {}",data.getData(),msg.getDeviceId());
                break;
            case CMDRSP:
                logger.info("receive Rsp from {} {}",msg.getDeviceId(),msg.getMsgId());
                NettyEvent cmdReqEvent = nettyEvents.get(msg.getMsgId());
                if(cmdReqEvent != null) {
                    cmdReqEvent.getCallBack().rcvCommandRsp(msg);
                }
                break;
            default:
                break;
        }
        if(devicesMap.containsKey(msg.getDeviceId())) {
            devicesMap.get(msg.getDeviceId()).setLastTimeMsgReceived(System.currentTimeMillis());
        }
    }

    public IotAckResult getDeviceServiceStatus(int deviceId) {
        if(!devicesMap.containsKey(deviceId))
            return ResultGenerator.genFailure(new IotException(ExceptionMeta.DeviceNotFound));
        DeviceContext context = devicesMap.get(deviceId);
        if(context.getServiceStatus() == DeviceServiceStatus.Up) {
            if(System.currentTimeMillis() - context.getLastTimeMsgReceived() > 30000) {     //30s
                context.setServiceStatus(DeviceServiceStatus.Down);
            }
        }
        return ResultGenerator.genSuccess(context.getServiceStatus());
    }

    @Override
    public void writeEvent(NettyEvent event) throws IotException {
        nettyServer.writeMessageToDevice(event.getIotMessage());
    }

    @Override
    public void connectEvent() {
    }

    @Override
    public void disconnectEvent() {
    }

    public int getPort() {
        return properties.getPort();
    }
}
