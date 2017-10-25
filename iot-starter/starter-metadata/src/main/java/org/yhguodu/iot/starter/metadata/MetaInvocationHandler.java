package org.yhguodu.iot.starter.metadata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yhguodu.iot.common.metadata.MetaService;
import org.yhguodu.iot.common.rpc.RpcMessage;
import org.yhguodu.iot.common.rpc.RpcMessageRequest;
import org.yhguodu.iot.starter.metadata.netty.MetadataRpcClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by yhguodu on 2017/10/25.
 */
public class MetaInvocationHandler implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(MetaInvocationHandler.class);

    private MetadataRpcClient metadataRpcClient;

    public MetaInvocationHandler(MetadataRpcClient client) {
        this.metadataRpcClient = client;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("method name {}",method.getName());
        RpcMessageRequest request = new RpcMessageRequest(1, MetaService.class.getName(),method.getName(),method.getParameterTypes(),args);
        metadataRpcClient.writeMessage(request);
        return 1l;
    }

}
