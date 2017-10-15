package org.yhguodu.iot.common.message;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Administrator on 2017/10/14.
 */
public class IotMessage implements Serializable {
    private static final long serialVersionUID = 7590999461767050471L;
    private static AtomicLong sequenceNum = new AtomicLong(0l);

    private final long msgId;
    private final int deviceId;
    private final IotMessageType msgType;

    public IotMessage(int deviceId,IotMessageType msgType) {
        this.deviceId = deviceId;
        this.msgType = msgType;
        msgId = sequenceNum.getAndIncrement();
    }

    public IotMessage(int deviceId,IotMessageType msgType,long msgId) {
        this.deviceId = deviceId;
        this.msgType = msgType;
        this.msgId = msgId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public IotMessageType getMsgType() {
        return msgType;
    }

    public long getMsgId() {
        return msgId;
    }

}
