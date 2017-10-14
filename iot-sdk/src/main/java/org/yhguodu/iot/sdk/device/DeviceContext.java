package org.yhguodu.iot.sdk.device;

import org.yhguodu.iot.common.device.IotDevice;
import org.yhguodu.iot.common.device.IotDeviceStatus;

/**
 * Created by Administrator on 2017/10/14.
 */
public class DeviceContext {
    private IotDevice device;
    private volatile IotDeviceStatus deviceStatus;

    public DeviceContext(IotDevice device) {
        this.device = device;
        deviceStatus = IotDeviceStatus.Null;
    }

    public IotDevice getDevice() {
        return device;
    }

    public void setDevice(IotDevice device) {
        this.device = device;
    }

    public IotDeviceStatus getDeviceStatus() {
        return deviceStatus;
    }

    public void updateDeviceStatus(IotDeviceStatus deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public boolean updateDeviceStatus(IotDeviceStatus newStatus,IotDeviceStatus expectedStatus) {
        if(deviceStatus != expectedStatus)
            return false;
        this.deviceStatus =  newStatus;
        return true;
    }
}
