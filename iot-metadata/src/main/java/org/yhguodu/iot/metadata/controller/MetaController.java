package org.yhguodu.iot.metadata.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yhguodu on 2017/10/12.
 */

@RestController
@RequestMapping("/metadata")
public class MetaController {

    @RequestMapping("/test")
    public String testMetadata() {
        return "test metadata at "+ System.currentTimeMillis();
    }

}
