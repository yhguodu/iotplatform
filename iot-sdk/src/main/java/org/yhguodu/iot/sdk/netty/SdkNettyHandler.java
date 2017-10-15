package org.yhguodu.iot.sdk.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yhguodu.iot.common.device.DeviceContext;
import org.yhguodu.iot.common.message.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/10/14.
 */
public class SdkNettyHandler extends ChannelInboundHandlerAdapter{
    private static final Logger logger = LoggerFactory.getLogger(SdkNettyHandler.class);
    private DeviceContext deviceContext;

    private EventHandler<IotMessage> eventHandler;

    public SdkNettyHandler(DeviceContext context,EventHandler<IotMessage> eventHandler) {
        this.deviceContext = context;
        this.eventHandler = eventHandler;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // Send the first message if this handler is a client-side handler.
       logger.info("update status");
       ctx.writeAndFlush(new AttachingMessage(deviceContext.getDevice()));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("channel inactive");
        super.channelInactive(ctx);
        ctx.channel().eventLoop().schedule(()->eventHandler.disconnectEvent(),3, TimeUnit.SECONDS);
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // Echo back the received object to the server.
        IotMessage iot = (IotMessage) msg;
        if(iot.getMsgType() == IotMessageType.CMDREQ) {
            logger.info("send resp to device {}",iot.getDeviceId());
            IotCmdReq req = (IotCmdReq) iot;
            ctx.write(new IotCmdRsp(req.getDeviceId(),req.getCommand(),req.getMsgId()));
        }
       //ctx.write(msg);
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

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx,Object evt) {
        if(evt instanceof IdleStateEvent)
            ctx.writeAndFlush(new KeepAliveMessage(deviceContext.getDevice().getDeviceId()));
    }
}
