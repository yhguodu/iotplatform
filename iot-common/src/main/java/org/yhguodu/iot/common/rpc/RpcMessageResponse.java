package org.yhguodu.iot.common.rpc;

/**
 * Created by yhguodu on 2017/10/24.
 */
public class RpcMessageResponse extends RpcMessage {
    public static final long serialVersionUID = 1002l;

    private int msgId;
    private Object response;

    public RpcMessageResponse() {}

    public RpcMessageResponse(int msgId, Object response) {
        this.msgId = msgId;
        this.response = response;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
