package org.yhguodu.iot.common.message;

import org.yhguodu.iot.common.device.IotDevice;

/**
 * Created by Administrator on 2017/10/14.
 */
public class AttachingMessage extends IotMessage  {
    private static final long serialVersionUID = 7590999461767050472L;
    private final IotDevice device;

    public AttachingMessage(IotDevice device) {
        super(IotMessageType.ATTACHING);
        this.device = device;
    }

    public IotDevice getDevice() {
        return device;
    }
}
