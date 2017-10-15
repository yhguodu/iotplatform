package org.yhguodu.iot.common.result;

/**
 * Created by Administrator on 2017/10/15.
 */
public class IotAckResult<T> {
    private int code;
    private T data;
    private String msg;

    public IotAckResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
