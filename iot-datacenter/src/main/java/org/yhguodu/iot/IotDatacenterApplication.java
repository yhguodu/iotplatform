package org.yhguodu.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.yhguodu.loadbalancer.MetadataConfiguration;

@SpringBootApplication
@EnableDiscoveryClient
//@RibbonClient(name = "iot-metadata",configuration = MetadataConfiguration.class)
@EnableFeignClients
public class IotDatacenterApplication {

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(IotDatacenterApplication.class, args);
	}
}
