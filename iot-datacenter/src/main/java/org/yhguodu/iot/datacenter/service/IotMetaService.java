package org.yhguodu.iot.datacenter.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yhguodu on 2017/10/16.
 */
@FeignClient(value = "iot-metadata")
public interface IotMetaService {

    @RequestMapping("/metadata/test")
    String test();
}
