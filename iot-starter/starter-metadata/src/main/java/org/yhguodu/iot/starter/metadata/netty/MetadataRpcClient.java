package org.yhguodu.iot.starter.metadata.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by yhguodu on 2017/10/25.
 */
public class MetadataRpcClient {


    private static final Logger logger = LoggerFactory.getLogger(MetadataRpcClient.class);
    private Bootstrap b;
    private ChannelFutureListener futureListener;
    private Channel channel;

    private String host;
    private int port;

    public MetadataRpcClient(String host,int port) {
        this.host = host;
        this.port = port;
    }

    public void init () {
        b = new Bootstrap();
        EventLoopGroup work = new NioEventLoopGroup();
        b.group(work)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast(new IdleStateHandler(20,0,0, TimeUnit.SECONDS))
                                .addLast(new ObjectEncoder())
                                .addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)))
                                .addLast(new MetaRpcMessageHandler());
                    }
                });

        b.option(ChannelOption.SO_KEEPALIVE,true);
        b.option(ChannelOption.TCP_NODELAY,true);

        futureListener = channelFuture -> {
            if(channelFuture.isSuccess()) {
                logger.info("connect suceess");
            }
            else {
                channelFuture.channel().eventLoop().schedule(()->doConnect(),3,TimeUnit.SECONDS);
            }
        };
    }


    private void doConnect() {
        ChannelFuture future = b.connect(host,port);
        future.addListener(futureListener);
        channel = future.channel();
    }

}
