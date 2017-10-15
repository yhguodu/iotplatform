package org.yhguodu.iot.devicecenter.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yhguodu.iot.common.message.IotMessage;
import org.yhguodu.iot.common.result.ICallBack;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by Administrator on 2017/10/15.
 */
public class CmdCallBack implements ICallBack<IotMessage> {
    private static final Logger logger = LoggerFactory.getLogger(CmdCallBack.class);

    private ReentrantLock lock = new ReentrantLock();
    private Condition waitForRsp = lock.newCondition();

    private int cmdTimeout;
    private volatile IotMessage message = null;

    public CmdCallBack(int cmdTimeout) {
        this.cmdTimeout = cmdTimeout;
    }

    public IotMessage getResponse() {
        if(message == null) {
            try {
                logger.info("get Response at {}",System.currentTimeMillis());
                lock.lock();
                waitForRsp.await(cmdTimeout, TimeUnit.SECONDS);
                return message;
            }
            catch(InterruptedException e) {

            }
            finally {
                lock.unlock();
            }
        }
        return message;
    }
    @Override
    public void rcvCommandRsp(IotMessage response) {
        try {
            logger.info("rcv command resp at {}",System.currentTimeMillis());
            lock.lock();
            message = response;
            waitForRsp.signal();
        }
        finally {
            lock.unlock();
        }
    }
}
