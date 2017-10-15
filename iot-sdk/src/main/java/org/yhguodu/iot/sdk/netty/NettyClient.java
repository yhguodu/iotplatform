package org.yhguodu.iot.sdk.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yhguodu.iot.common.device.DeviceContext;
import org.yhguodu.iot.common.message.EventHandler;
import org.yhguodu.iot.common.message.IotMessage;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/10/13.
 */
public class NettyClient  {

    private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);
    private final String host;
    private final int port;
    private final DeviceContext deviceContext;
    private EventHandler<IotMessage> eventHandler;

    private Bootstrap bootstrap;
    private ChannelFutureListener futureListener;
    private Channel channel;

    public NettyClient(String host, int port, DeviceContext deviceContext, EventHandler<IotMessage> eventHandler) {
        this.host = host;
        this.port = port;
        this.deviceContext = deviceContext;
        this.eventHandler = eventHandler;
    }

    public void init() {
        EventLoopGroup group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new IdleStateHandler(20,0,0,TimeUnit.SECONDS),
                                new ObjectEncoder(),
                                new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
                                new SdkNettyHandler(deviceContext,eventHandler));
                    }
                });

        //设置TCP协议的属性
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
//        bootstrap.option(ChannelOption.SO_TIMEOUT, 5000);

        futureListener = new ChannelFutureListener() {
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if(channelFuture.isSuccess()) {
                    logger.info("connect success");
                }
                else {
                    channelFuture.channel().eventLoop().schedule(()->doConnect(),3, TimeUnit.SECONDS);
                }
            }
        };
    }

    public void writeMessage(IotMessage message) {
        if(channel != null) {
            channel.writeAndFlush(message);
            return;
        }
        logger.info("write message error");
    }
    public void doConnect() {
        ChannelFuture future = bootstrap.connect(host,port);
        future.addListener(futureListener);
        channel = future.channel();
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
