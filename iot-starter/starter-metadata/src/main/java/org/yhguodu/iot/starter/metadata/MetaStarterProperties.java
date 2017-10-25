package org.yhguodu.iot.starter.metadata;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by yhguodu on 2017/10/25.
 */
@ConfigurationProperties(prefix = "iot.starter.metadata")
public class MetaStarterProperties {
    private int port;
    private String host;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
