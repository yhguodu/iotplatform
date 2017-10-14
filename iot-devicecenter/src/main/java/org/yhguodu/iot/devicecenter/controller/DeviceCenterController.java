package org.yhguodu.iot.devicecenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
}
