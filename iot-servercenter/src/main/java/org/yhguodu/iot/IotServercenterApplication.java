package org.yhguodu.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class IotServercenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(IotServercenterApplication.class, args);
	}
}
