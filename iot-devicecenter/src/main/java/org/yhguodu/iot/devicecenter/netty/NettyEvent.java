package org.yhguodu.iot.devicecenter.netty;

import io.netty.channel.Channel;
import org.yhguodu.iot.common.message.IotMessage;
import org.yhguodu.iot.common.result.ICallBack;

/**
 * Created by Administrator on 2017/10/15.
 */
public class NettyEvent {
    private final IotMessage iotMessage;
    private final Channel channel;
    private final ICallBack<IotMessage> callBack;

    public NettyEvent(IotMessage message ,Channel channel,ICallBack<IotMessage> callBack) {
        this.iotMessage = message;
        this.channel = channel;
        this.callBack = callBack;
    }

    public IotMessage getIotMessage() {
        return iotMessage;
    }

    public Channel getChannel() {
        return channel;
    }

    public ICallBack<IotMessage> getCallBack() {
        return callBack;
    }
}
