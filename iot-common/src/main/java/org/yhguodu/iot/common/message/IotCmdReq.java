package org.yhguodu.iot.common.message;

/**
 * Created by Administrator on 2017/10/15.
 */
public class IotCmdReq extends IotMessage {
    private static final long serialVersionUID = 7590999461767050476L;
    private final String command;

    public IotCmdReq(int deviceId, String command) {
        super(deviceId,IotMessageType.CMDREQ);
        this.command = command;
   }

    public String getCommand() {
        return command;
    }

}
