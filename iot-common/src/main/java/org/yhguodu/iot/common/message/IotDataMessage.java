package org.yhguodu.iot.common.message;

/**
 * Created by Administrator on 2017/10/14.
 */
public class IotDataMessage extends IotMessage {
    private static final long serialVersionUID = 7590999461767050473L;
    private final String data;

    public IotDataMessage(int deviceId,String data) {
        super(deviceId,IotMessageType.DATA);
        this.data = data;
    }

    public String getData() {
        return data;
    }

}
