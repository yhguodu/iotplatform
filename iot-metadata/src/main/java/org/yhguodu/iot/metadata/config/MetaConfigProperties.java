package org.yhguodu.iot.metadata.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by yhguodu on 2017/10/24.
 */

@ConfigurationProperties(prefix = "metadata.rpc.server")
public class MetaConfigProperties {
    private int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
