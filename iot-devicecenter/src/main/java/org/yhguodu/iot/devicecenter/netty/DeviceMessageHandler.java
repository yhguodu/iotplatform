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
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        IotMessage result = (IotMessage)msg;
        if(result.getMsgType() == IotMessageType.ATTACHING) {
            logger.info("device id {}", ((AttachingMessage) result).getDevice().getDeviceId());
        }
        else if(result.getMsgType() == IotMessageType.DATA) {
            logger.info("data {}",((IotDataMessage)result).getData());
        }
        else if(result.getMsgType() == IotMessageType.KEEPALIVE) {
            logger.info("keeping alive {}",((KeepAliveMessage)result).getDeviceId());
        }
//        // Echo back the received object to the client.
//        ctx.write(msg);
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
