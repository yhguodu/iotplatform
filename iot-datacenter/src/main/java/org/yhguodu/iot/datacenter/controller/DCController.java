package org.yhguodu.iot.datacenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by yhguodu on 2017/10/13.
 */
@RestController
@RequestMapping("/dccenter")
public class DCController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test() {
        String meta = restTemplate.getForObject("http://iot-metadata/metadata/test",String.class);
        return "dccenter:"+meta;
    }

    @RequestMapping(value = "/user/test",method = RequestMethod.GET)
    public String testUser() {
        String meta = restTemplate.getForObject("http://user/metadata/test",String.class);
        return "dccenter:"+meta;
    }
}
