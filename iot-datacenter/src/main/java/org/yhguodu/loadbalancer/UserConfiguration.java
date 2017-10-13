package org.yhguodu.loadbalancer;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yhguodu on 2017/10/13.
 */
@Configuration
public class UserConfiguration {

    @Autowired
    IClientConfig config;

    @Bean
    public IPing ribbonPing (IClientConfig config) {
        return new PingUrl();//we override default Iping which is a NoOpPing
    }

    @Bean
    public IRule ribbonRule(IClientConfig config) {
        return new AvailabilityFilteringRule(); // we override the default ZoneAvoidanceRule
    }

}
