package org.yhguodu.iot.common.rpc;

import java.util.Arrays;

/**
 * Created by yhguodu on 2017/10/24.
 */
public class RpcMessageRequest extends RpcMessage {
    public static final long serialVersionUID = 1001l;
    private int msgId;
    private String className;
    private String methodName;
    private Class[] paramTypes;
    private Object[] paramValues;

    public RpcMessageRequest() {}

    public RpcMessageRequest(int msgId,String className,String methodName,Class[] parameterTypes,Object[] paramValues) {
        this.msgId = msgId;
        this.className = className;
        this.methodName = methodName;
        this.paramTypes = parameterTypes;
        this.paramValues = paramValues;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Object[] getParamValues() {
        return paramValues;
    }

    public void setParamValues(Object[] paramValues) {
        this.paramValues = paramValues;
    }

    @Override
    public String toString() {
        return "RpcMessageRequest{" +
                "msgId=" + msgId +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", paramTypes=" + Arrays.toString(paramTypes) +
                ", paramValues=" + Arrays.toString(paramValues) +
                '}';
    }
}
