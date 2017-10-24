package org.yhguodu.iot.metadata.common;

import io.netty.channel.Channel;
import org.yhguodu.iot.common.rpc.RpcMessage;

/**
 * Created by yhguodu on 2017/10/24.
 */
public class RpcEvent {
    private final RpcMessage message;
    private final Channel channel;

    public RpcEvent(RpcMessage message, Channel channel) {
        this.message = message;
        this.channel = channel;
    }

    public RpcMessage getMessage() {
        return message;
    }

    public Channel getChannel() {
        return channel;
    }
}
