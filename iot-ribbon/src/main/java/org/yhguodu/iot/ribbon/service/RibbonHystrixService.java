package org.yhguodu.iot.ribbon.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.yhguodu.iot.ribbon.core.User;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RibbonHystrixService {
    private static Logger logger = Logger.getLogger(RibbonHystrixService.class.getName());
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 使用@HystrixCommand注解指定当该方法发生异常时调用的方法
     * @param id id
     * @return 通过id查询到的用户
     */
    @HystrixCommand(fallbackMethod = "fallback")
    public User findById(Long id) {
        return this.restTemplate.getForObject("http://openapi-service-business-test/" + id, User.class);
    }
    /**
     * hystrix fallback方法
     * @param id id
     * @return 默认的用户
     */
    public User fallback(Long id) {
        logger.log(Level.INFO,"异常发生，进入fallback方法，接收的参数：id："+id);
        User user = new User();
        user.setId(-1L);
        user.setUsername("default username");
        user.setAge(0);
        return user;
    }
}
