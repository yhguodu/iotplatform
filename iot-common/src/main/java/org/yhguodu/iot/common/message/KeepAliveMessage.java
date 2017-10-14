package org.yhguodu.iot.common.message;

/**
 * Created by Administrator on 2017/10/14.
 */
public class KeepAliveMessage extends IotMessage {
    private static final long serialVersionUID = 7590999461767050475L;
    private int deviceId;

    public KeepAliveMessage(int deviceId) {
        super(IotMessageType.KEEPALIVE);
        this.deviceId = deviceId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }
}
