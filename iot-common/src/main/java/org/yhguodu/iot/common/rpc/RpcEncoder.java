package org.yhguodu.iot.common.rpc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.yhguodu.iot.common.serialize.protostuff.SerializationUtils;

/**
 * Created by yhguodu on 2017/10/26.
 */
public class RpcEncoder extends MessageToByteEncoder {

    private Class<?> genericClass;

    public RpcEncoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    public void encode(ChannelHandlerContext ctx, Object object, ByteBuf buff) throws Exception {
        if(genericClass.isInstance(object)) {
            byte[] data = SerializationUtils.serialize(object);
            buff.writeInt(data.length);
            buff.writeBytes(data);
        }
    }
}
