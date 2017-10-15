package org.yhguodu.iot.common.result;

/**
 * Created by Administrator on 2017/10/15.
 */
public interface ICallBack<T> {
    void rcvCommandRsp(T response);
}
