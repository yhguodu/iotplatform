package org.yhguodu.iot.devicecenter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Service;
import org.yhguodu.iot.common.device.IotDevice;
import org.yhguodu.iot.devicecenter.config.DeviceCenterProperties;
import org.yhguodu.iot.devicecenter.netty.NettyServer;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/10/14.
 */

@Service
public class DeviceService implements SmartLifecycle {
    private static final Logger logger = LoggerFactory.getLogger(DeviceService.class);
    @Autowired
    private DeviceCenterProperties properties;

    private NettyServer nettyServer;
    private ConcurrentHashMap<Integer,IotDevice> devicesMap = new ConcurrentHashMap<Integer, IotDevice>();
    public int getPort() {
        return properties.getPort();
    }
    private volatile boolean isRunning;
    public boolean isAutoStartup() {
        return true;
    }

    public void stop(Runnable runnable) {
    }

    public void start() {
        isRunning = true;
        nettyServer = new NettyServer(properties.getPort());
        logger.info("nett port {}",properties.getPort());
        nettyServer.run();

    }

    public void stop() {
        isRunning = false;
    }

    public boolean isRunning() {
        return false;
    }

    public int getPhase() {
        return 0;
    }
}
