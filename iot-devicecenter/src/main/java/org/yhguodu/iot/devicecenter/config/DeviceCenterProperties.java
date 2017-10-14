package org.yhguodu.iot.devicecenter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Administrator on 2017/10/14.
 */
@ConfigurationProperties(prefix = "devicecenter.netty")
public class DeviceCenterProperties {
    private int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
