package org.yhguodu.iot.common.message;

/**
 * Created by Administrator on 2017/10/15.
 */
public class IotCmdRsp extends IotMessage {
    private static final long serialVersionUID = 7590999461767050477L;

    private final String cmdRsp;

    public IotCmdRsp(int deviceId, String cmdRsp,long msgId) {
        super(deviceId,IotMessageType.CMDRSP,msgId);
        this.cmdRsp = cmdRsp;
    }

    public String getCmdRsp() {
        return cmdRsp;
    }
}
