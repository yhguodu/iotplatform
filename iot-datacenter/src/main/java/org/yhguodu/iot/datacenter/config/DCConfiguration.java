package org.yhguodu.iot.datacenter.config;

import com.netflix.loadbalancer.DynamicServerListLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yhguodu.iot.datacenter.loadbalance.DCLoadBalance;

/**
 * Created by Administrator on 2017/10/12.
 */
//@Configuration
public class DCConfiguration {
    @Bean
    public ILoadBalancer loadBalancer() {
        return new DCLoadBalance();
    }
}
