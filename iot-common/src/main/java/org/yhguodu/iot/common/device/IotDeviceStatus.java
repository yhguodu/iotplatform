package org.yhguodu.iot.common.device;

/**
 * Created by Administrator on 2017/10/14.
 */
public enum IotDeviceStatus {
    Null,
    Attaching_Needed,
    Idle,
    Attaching,
    Attached_Waited,
    Detaching,
    Attached;

    IotDeviceStatus() {}
}
