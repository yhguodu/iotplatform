package org.yhguodu.iot.common.message;

/**
 * Created by Administrator on 2017/10/14.
 */
public class IotDataMessage extends IotMessage {
    private static final long serialVersionUID = 7590999461767050473L;
    private int deviceId;
    private String data;

    public IotDataMessage(int deviceId,String data) {
        super(IotMessageType.DATA);
        this.deviceId = deviceId;
        this.data = data;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
