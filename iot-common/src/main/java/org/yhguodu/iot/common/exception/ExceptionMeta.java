package org.yhguodu.iot.common.exception;

/**
 * Created by Administrator on 2017/10/14.
 */
public enum ExceptionMeta {
    FileNotFound(100000,"File Not Found"),
    FileIOError(100002,"File Read/Write Eroor");

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
