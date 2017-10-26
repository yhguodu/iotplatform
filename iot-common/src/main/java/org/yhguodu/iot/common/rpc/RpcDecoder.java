package org.yhguodu.iot.common.rpc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.yhguodu.iot.common.serialize.protostuff.SerializationUtils;

import java.util.List;

/**
 * Created by yhguodu on 2017/10/26.
 */
public class RpcDecoder extends ByteToMessageDecoder {

    private Class<?> genericClass;

    public RpcDecoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buff, List<Object> var3) throws Exception {
        if(buff.readableBytes() < 4) {
            return;
        }
        buff.markReaderIndex();

        int length = buff.readInt();

        if(buff.readableBytes() < length) {
            buff.resetReaderIndex();
            return;
        }

        byte[] data = new byte[length];
        buff.readBytes(data);

        Object object= SerializationUtils.deserialize(data,genericClass);
        var3.add(object);

    }
}
