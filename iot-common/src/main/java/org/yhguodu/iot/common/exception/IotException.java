package org.yhguodu.iot.common.exception;

/**
 * Created by Administrator on 2017/10/14.
 */
public class IotException extends Exception{
    private final int code;

    public IotException(int code,String message) {
        super(message);
        this.code = code;
    }

    public IotException(ExceptionMeta meta) {
        super(meta.getMessage());
        this.code = meta.getCode();
    }

    public int getCode() {
        return code;
    }
}
