package org.yhguodu.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.yhguodu.iot.devicecenter.config.DeviceCenterProperties;

@SpringBootApplication
@EnableConfigurationProperties(DeviceCenterProperties.class)
public class IotDevicecenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(IotDevicecenterApplication.class, args);
	}
}
