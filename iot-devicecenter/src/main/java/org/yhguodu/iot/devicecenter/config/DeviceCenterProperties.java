package org.yhguodu.iot.devicecenter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Administrator on 2017/10/14.
 */
@ConfigurationProperties(prefix = "devicecenter.netty")
public class DeviceCenterProperties {
    private int port;

    private int cmdTimeout;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getCmdTimeout() {
        return cmdTimeout;
    }

    public void setCmdTimeout(int cmdTimeout) {
        this.cmdTimeout = cmdTimeout;
    }
}
