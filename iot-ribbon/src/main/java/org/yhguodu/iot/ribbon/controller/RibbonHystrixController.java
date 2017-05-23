package org.yhguodu.iot.ribbon.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yhguodu.iot.ribbon.core.User;
import org.yhguodu.iot.ribbon.service.RibbonHystrixService;

import java.util.logging.Logger;

@RestController
public class RibbonHystrixController {

    private static  Logger logger = Logger.getLogger(RibbonHystrixController.class.getName());
    @Autowired
    private RibbonHystrixService ribbonHystrixService;

    @RequestMapping(value= "/ribbon/{id}",method = RequestMethod.GET)
    public User findById(@PathVariable long id) {
        return ribbonHystrixService.findById(id);
    }
}
