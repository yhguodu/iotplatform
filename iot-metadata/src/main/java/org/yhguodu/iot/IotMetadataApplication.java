package org.yhguodu.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class IotMetadataApplication {

	public static void main(String[] args) {
		SpringApplication.run(IotMetadataApplication.class, args);
	}
}
