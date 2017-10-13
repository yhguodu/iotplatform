package org.yhguodu.loadbalancer;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yhguodu on 2017/10/13.
 */
@Configuration
public class MetadataConfiguration {

    @Autowired
    IClientConfig config;

    @Bean
    @ConditionalOnMissingBean
    public IRule ribbonRule(IClientConfig config) {
        System.out.println("随机的。。。。");
        return new RandomRule();
    }
}
