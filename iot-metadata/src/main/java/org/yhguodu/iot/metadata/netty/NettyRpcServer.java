package org.yhguodu.iot.metadata.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yhguodu.iot.common.message.EventHandler;
import org.yhguodu.iot.common.rpc.RpcDecoder;
import org.yhguodu.iot.common.rpc.RpcEncoder;
import org.yhguodu.iot.common.rpc.RpcMessageRequest;
import org.yhguodu.iot.common.rpc.RpcMessageResponse;
import org.yhguodu.iot.metadata.common.RpcEvent;


/**
 * Created by yhguodu on 2017/10/24.
 */
public class NettyRpcServer {

    private static final Logger logger = LoggerFactory.getLogger(NettyRpcServer.class);

    private int port;
    private EventHandler<RpcEvent> handler;

    private RpcServerMsgHandler msgHandler;

    public NettyRpcServer(int port, EventHandler<RpcEvent> handler) {
        this.port = port;
        this.handler = handler;
        msgHandler = new RpcServerMsgHandler(handler);
    }

    public void init () {
        ServerBootstrap b = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup work = new NioEventLoopGroup();

        try {
            b.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new RpcEncoder(RpcMessageResponse.class))
                                    .addLast(new LengthFieldBasedFrameDecoder(65536,0,4,0,0))
                                    .addLast(new RpcDecoder(RpcMessageRequest.class))
                                    .addLast(msgHandler);
                        }
                    });
            b.bind(port).sync().channel().closeFuture().sync();

        }
        catch(InterruptedException e){
            logger.info("InterruptedException",e);
        }
        finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

}
