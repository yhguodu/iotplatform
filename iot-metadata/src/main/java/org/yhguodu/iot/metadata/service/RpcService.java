package org.yhguodu.iot.metadata.service;

import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.yhguodu.iot.common.exception.IotException;
import org.yhguodu.iot.common.message.EventHandler;
import org.yhguodu.iot.common.rpc.RpcMessageRequest;
import org.yhguodu.iot.common.rpc.RpcMessageResponse;
import org.yhguodu.iot.metadata.common.RpcEvent;
import org.yhguodu.iot.metadata.config.MetaConfigProperties;
import org.yhguodu.iot.metadata.netty.NettyRpcServer;

import java.util.concurrent.*;

/**
 * Created by yhguodu on 2017/10/24.
 */

@Service
public class RpcService implements ApplicationContextAware,InitializingBean,EventHandler<RpcEvent> {

    private static final Logger logger = LoggerFactory.getLogger(RpcService.class);

    private ApplicationContext appContext;

    private NettyRpcServer rpcServer;

    @Autowired
    private MetaConfigProperties properties;

    private ThreadPoolExecutor threadPool;

    @Override
    public void afterPropertiesSet() throws Exception {
        rpcServer = new NettyRpcServer(properties.getPort(),this);
        threadPool = new ThreadPoolExecutor(10,20,60, TimeUnit.SECONDS,new LinkedBlockingQueue<>(100));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appContext = applicationContext;
    }

    public void executeRequest(RpcMessageRequest rpcMessageRequest,Channel channel) {

    }


    @Override
    public void readEvent(final RpcEvent message) {
        threadPool.submit(() -> {
            RpcMessageRequest request = (RpcMessageRequest) message.getMessage();
            Channel channel = message.getChannel();
            logger.info("request {}",request.toString());

            RpcMessageResponse respones = new RpcMessageResponse(request.getMsgId(),request.getMethodName());
            try {
                writeEvent(new RpcEvent(respones, channel));
            }
            catch(IotException e) {
                logger.info("iotException {}",e);
            }
        });
    }

    @Override
    public void writeEvent(RpcEvent message) throws IotException {

    }

    @Override
    public void connectEvent() {

    }

    @Override
    public void disconnectEvent() {

    }
}
