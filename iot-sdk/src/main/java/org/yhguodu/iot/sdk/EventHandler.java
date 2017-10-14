package org.yhguodu.iot.sdk;

/**
 * Created by Administrator on 2017/10/14.
 */
public interface EventHandler<T> {
    void readEvent(T message);
    void writeEvent(T message);
    void connectEvent();
    void disconnectEvent();
}
