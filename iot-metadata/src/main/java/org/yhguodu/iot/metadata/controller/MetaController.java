package org.yhguodu.iot.metadata.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yhguodu.iot.metadata.service.MetaService;

/**
 * Created by yhguodu on 2017/10/12.
 */

@RestController
@RequestMapping("/metadata")
public class MetaController {

    private static final Logger logger = LoggerFactory.getLogger(MetaController.class);
    @Autowired
    MetaService metaService;
    @RequestMapping("/test")
    public String testMetadata() {
        return "test metadata at "+ System.currentTimeMillis();
    }


    @RequestMapping("redis/put")
    public String testRedis(@RequestParam("key")String key,@RequestParam("value")long value) {
        if(metaService.putData(key,value))
            return "success:"+ System.currentTimeMillis();

        return "fail:"+System.currentTimeMillis();
    }

    @RequestMapping("redis/get")
    public long testRedisGet(@RequestParam("key") String key) {
        long value = metaService.getData(key);
        logger.info("value {}",value);
        return value;
    }
}
