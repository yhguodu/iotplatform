package org.yhguodu.iot.devicecenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yhguodu.iot.common.result.IotAckResult;
import org.yhguodu.iot.devicecenter.service.DeviceService;

/**
 * Created by Administrator on 2017/10/14.
 */
@RestController
public class DeviceCenterController {
    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test() {
        return "test device controller" + deviceService.getPort();
    }

    @RequestMapping(value = "cmd/{deviceId}",method = RequestMethod.GET)
    public IotAckResult sendCmdToDevice(@PathVariable("deviceId")int deviceId, @RequestParam("cmd")String req) {
        return deviceService.cmdDevice(deviceId,req);
    }

    @RequestMapping(value="/device/servicestatus/{deviceId}",method = RequestMethod.GET)
    public IotAckResult getDeviceServiceStatus(@PathVariable("deviceId")int deviceId) {
        return deviceService.getDeviceServiceStatus(deviceId);
    }

}
