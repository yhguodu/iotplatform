package org.yhguodu.iot.datacenter.loadbalancer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequestFactory;
import org.springframework.cloud.client.loadbalancer.RestTemplateCustomizer;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * Created by yhguodu on 2017/10/13.
 */
@Configuration
public class DcAutoConfiguration {

    @Bean
    @Qualifier("dcLoadBalancerInterceptor")
    public LoadBalancerInterceptor loadBalancerInterceptor(LoadBalancerClient loadBalancer, LoadBalancerRequestFactory requestFactory) {
        return new DcLoadBalancerInterceptor(loadBalancer,requestFactory);
    }

    @Bean
    public RestTemplateCustomizer restTemplateCustomizer(@Qualifier("dcLoadBalancerInterceptor") final LoadBalancerInterceptor loadBalancerInterceptor) {
        return new RestTemplateCustomizer() {
            public void customize(RestTemplate restTemplate) {
                ArrayList list = new ArrayList(restTemplate.getInterceptors());
                list.add(loadBalancerInterceptor);
                restTemplate.setInterceptors(list);
            }
        };
    }

    @Bean
    public LoadBalancerClient loadBalancerClient(SpringClientFactory factory) {
        return new DcRibbonLoadBalancerClient(factory);
    }
}
