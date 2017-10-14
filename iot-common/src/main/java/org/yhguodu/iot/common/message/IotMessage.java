package org.yhguodu.iot.common.message;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/14.
 */
public class IotMessage implements Serializable {
    private static final long serialVersionUID = 7590999461767050471L;
    private IotMessageType msgType;

    public IotMessage(IotMessageType msgType) {
        this.msgType = msgType;
    }

    public IotMessageType getMsgType() {
        return msgType;
    }

    public void setMsgType(IotMessageType msgType) {
        this.msgType = msgType;
    }
}
