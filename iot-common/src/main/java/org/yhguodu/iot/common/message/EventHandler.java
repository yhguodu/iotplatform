package org.yhguodu.iot.common.message;

import org.yhguodu.iot.common.exception.IotException;

/**
 * Created by Administrator on 2017/10/15.
 */
public interface EventHandler<T> {
    void readEvent(T message);
    void writeEvent(T message) throws IotException;
    void connectEvent();
    void disconnectEvent();
}
