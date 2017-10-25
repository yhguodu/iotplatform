package org.yhguodu.iot.metadata.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yhguodu.iot.common.message.EventHandler;
import org.yhguodu.iot.common.rpc.RpcMessage;
import org.yhguodu.iot.common.rpc.RpcMessageResponse;
import org.yhguodu.iot.metadata.common.RpcEvent;

/**
 * Created by yhguodu on 2017/10/24.
 */
public class RpcServerMsgHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(RpcServerMsgHandler.class);

    private EventHandler<RpcEvent> handler;

    public RpcServerMsgHandler(EventHandler<RpcEvent> handler) {
        this.handler = handler;
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelInactive();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        handler.readEvent(new RpcEvent((RpcMessage)msg,ctx.channel()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.fireExceptionCaught(cause);
    }
}
