package org.yhguodu.iot.common.result;

import org.yhguodu.iot.common.exception.IotException;

/**
 * Created by Administrator on 2017/10/15.
 */
public class ResultGenerator {
    private ResultGenerator() {}

    public static <T> IotAckResult<T> genSuccess(T data) {
        return new IotAckResult<T>(200,"",data);
    }

    public static <T> IotAckResult<T> genFailure(IotException e) {
        return new IotAckResult<T>(e.getCode(),e.getMessage(),null);
    }
}
