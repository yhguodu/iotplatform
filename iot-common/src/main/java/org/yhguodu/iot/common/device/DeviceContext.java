package org.yhguodu.iot.common.device;

/**
 * Created by Administrator on 2017/10/14.
 */
public class DeviceContext {
    private IotDevice device;
    //private volatile IotDeviceStatus deviceStatus;
    private DeviceServiceStatus serviceStatus;

    private volatile long lastTimeMsgReceived = 0l;

    public DeviceContext(IotDevice device) {
        this.device = device;
//        deviceStatus = IotDeviceStatus.Null;
        serviceStatus = DeviceServiceStatus.Down;
    }

    public IotDevice getDevice() {
        return device;
    }

    public void setDevice(IotDevice device) {
        this.device = device;
    }

//    public IotDeviceStatus getDeviceStatus() {
//        return deviceStatus;
//    }
//
//    public void updateDeviceStatus(IotDeviceStatus deviceStatus) {
//        this.deviceStatus = deviceStatus;
//    }
//
//    public boolean updateDeviceStatus(IotDeviceStatus newStatus,IotDeviceStatus expectedStatus) {
//        if(deviceStatus != expectedStatus)
//            return false;
//        this.deviceStatus =  newStatus;
//        return true;
//    }


    public DeviceServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(DeviceServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public long getLastTimeMsgReceived() {
        return lastTimeMsgReceived;
    }

    public void setLastTimeMsgReceived(long lastTimeMsgReceived) {
        this.lastTimeMsgReceived = lastTimeMsgReceived;
    }
}
