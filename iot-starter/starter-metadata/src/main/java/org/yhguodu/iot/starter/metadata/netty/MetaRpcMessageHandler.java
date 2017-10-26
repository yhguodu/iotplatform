package org.yhguodu.iot.starter.metadata.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yhguodu.iot.common.rpc.RpcMessageResponse;

import java.net.SocketAddress;

/**
 * Created by yhguodu on 2017/10/25.
 */
public class MetaRpcMessageHandler extends SimpleChannelInboundHandler<RpcMessageResponse> {

    private static final Logger logger = LoggerFactory.getLogger(MetaRpcMessageHandler.class);

    private volatile Channel channel;
    private SocketAddress remotePeer;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.remotePeer = this.channel.remoteAddress();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        this.channel = ctx.channel();
    }
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcMessageResponse rpcMessageResponse) throws Exception {

    }
}
