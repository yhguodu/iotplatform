package org.yhguodu.iot.datacenter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.yhguodu.iot.common.metadata.MetaService;
import org.yhguodu.iot.datacenter.service.IotMetaService;

/**
 * Created by Administrator on 2017/10/12.
 */
@RestController
@RequestMapping("datacenter")
public class DataCenterController {

    private static final Logger logger = LoggerFactory.getLogger(DataCenterController.class);
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IotMetaService metaService;

    @Autowired
    private MetaService metaRpcService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String testRestTemplate() {
        String metadata = restTemplate.getForObject("http://iot-metadata/metadata/test",String.class);

        return "datacenter;"+metadata;
    }

    @RequestMapping(value = "/test/feign",method = RequestMethod.GET)
    public String testFeign() {
        return metaService.test();
    }

    @RequestMapping(value = "test/rpc",method = RequestMethod.GET)
    public String testRpc() {
        metaRpcService.getData("hellooo");
        return "success";
    }
}
