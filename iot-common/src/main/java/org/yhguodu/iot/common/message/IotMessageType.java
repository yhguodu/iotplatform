package org.yhguodu.iot.common.message;

/**
 * Created by Administrator on 2017/10/14.
 */
public enum IotMessageType {
    ATTACHING,
    ATTACHED,
    DATA,
    DETACH,
    KEEPALIVE,
    CMDREQ,
    CMDRSP,
    CONFIG;

    IotMessageType() {}
}
