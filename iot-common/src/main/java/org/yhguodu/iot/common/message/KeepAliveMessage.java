package org.yhguodu.iot.common.message;

/**
 * Created by Administrator on 2017/10/14.
 */
public class KeepAliveMessage extends IotMessage {
    private static final long serialVersionUID = 7590999461767050475L;

    public KeepAliveMessage(int deviceId) {
        super(deviceId,IotMessageType.KEEPALIVE);
    }

}
