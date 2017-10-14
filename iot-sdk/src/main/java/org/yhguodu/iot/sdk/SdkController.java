package org.yhguodu.iot.sdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yhguodu.iot.common.device.IotDevice;
import org.yhguodu.iot.common.message.IotDataMessage;
import org.yhguodu.iot.common.message.IotMessage;
import org.yhguodu.iot.sdk.device.DeviceContext;
import org.yhguodu.iot.sdk.netty.NettyClient;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/10/14.
 */
public class SdkController implements EventHandler<IotMessage> {
    private static final Logger logger = LoggerFactory.getLogger(SdkController.class);
    private KeyProperties keyProperties;

    private DeviceContext deviceContext;

    private ExecutorService nettyExecutor ;
    private NettyClient nettyClient;
    public SdkController(KeyProperties keyProperties) {
        this.keyProperties = keyProperties;
        deviceContext = new DeviceContext(newIotDevice());

        nettyClient = new NettyClient(keyProperties.getProperty("iot.server.host","localhost"),
                keyProperties.getIntProperty("iot.server.port",8007),
                deviceContext,this);
        nettyExecutor = Executors.newSingleThreadExecutor();
    }

    public void launch() {
        nettyClient.init();
        nettyExecutor.submit(()->nettyClient.doConnect());
        final Random r = new Random(100);
        final int deviceId = deviceContext.getDevice().getDeviceId();
        ScheduledExecutorService dataSendingTask = Executors.newScheduledThreadPool(1);
        dataSendingTask.scheduleAtFixedRate(()->{
            String data = String.valueOf(r.nextInt());
            writeEvent(new IotDataMessage(deviceId,data));
        },0,10, TimeUnit.SECONDS);
    }


    private IotDevice newIotDevice() {
        int deviceId = keyProperties.getIntProperty("iot.device.id",0);
        String deviceModel = keyProperties.getProperty("iot.device.model","default");
        String industry = keyProperties.getProperty("iot.device.industrycode","default");
        String protocol = keyProperties.getProperty("iot.device.protocol","default");

        return new IotDevice(deviceId,deviceModel,industry,protocol);
    }

    @Override
    public void readEvent(IotMessage message) {

    }

    @Override
    public void writeEvent(IotMessage message) {
        nettyClient.writeMessage(message);
    }

    @Override
    public void connectEvent() {
        nettyClient.doConnect();
    }

    @Override
    public void disconnectEvent() {
        nettyClient.setChannel(null);
        nettyClient.doConnect();
    }
}
