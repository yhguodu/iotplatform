package org.yhguodu.iot.devicecenter.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yhguodu.iot.common.exception.ExceptionMeta;
import org.yhguodu.iot.common.exception.IotException;
import org.yhguodu.iot.common.message.EventHandler;
import org.yhguodu.iot.common.message.IotMessage;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/10/14.
 */
public class NettyServer {

    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);
    private int port;
    private final EventHandler<NettyEvent> eventHandler;
    private final DeviceMessageHandler deviceMessageHandler;

    private ConcurrentHashMap<Integer,Channel> deviceChannelPair;
    public NettyServer(int port,EventHandler<NettyEvent> eventHandler) {
        this.port = port;
        this.eventHandler = eventHandler;
        deviceMessageHandler = new DeviceMessageHandler(eventHandler);
        deviceChannelPair = new ConcurrentHashMap<>();
    }

    public void run() {
        EventLoopGroup boosGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(boosGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new ObjectEncoder(),
                                    new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
                                    deviceMessageHandler
                            );
                        }
                    });
            b.bind(port).sync().channel().closeFuture().sync();
        }
        catch(InterruptedException e) {

        }
        finally {
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public boolean writeMessageToDevice(IotMessage message) throws IotException {
        if(!deviceChannelPair.containsKey(message.getDeviceId()))
            throw new IotException(ExceptionMeta.DeviceNotFound);

        Channel channel = deviceChannelPair.get(message.getDeviceId());
        channel.writeAndFlush(message);
        return true;
    }
    public void addDeviceChannelPair(int deviceId,Channel channel) {
        deviceChannelPair.put(deviceId,channel);
    }
}
