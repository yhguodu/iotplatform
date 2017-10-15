package org.yhguodu.iot.common.exception;

/**
 * Created by Administrator on 2017/10/14.
 */
public enum ExceptionMeta {
    FileNotFound(100000,"File Not Found"),
    FileIOError(100002,"File Read/Write Eroor"),

    DeviceNotFound(100100,"Device Not Found"),
    DeviceNotActive(100101,"Device Not Active Moment"),

    CommandRspNotReceived(100201,"Command Response Not Received,Timeout"),
    CommandSendingError(100202,"Command Sending Eroor");

    private int code;
    private String message;

    ExceptionMeta(int code,String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
