package org.yhguodu.iot.devicecenter.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yhguodu.iot.common.message.*;

/**
 * Created by Administrator on 2017/10/14.
 */
public class DeviceMessageHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(DeviceMessageHandler.class);

    private EventHandler<NettyEvent> eventHandler;

    public DeviceMessageHandler(EventHandler<NettyEvent> eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        NettyEvent event = new NettyEvent((IotMessage)msg,ctx.channel(),null);
        eventHandler.readEvent(event);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
