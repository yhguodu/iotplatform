package org.yhguodu.iot.metadata.service;

/**
 * Created by yhguodu on 2017/10/23.
 */
public interface MetaService {

    /**
     * get data from redis
     * @param key
     * @return
     */
    long getData(String key);


    /**
     * put data to redis
     * @param key
     * @param value
     * @return
     */
    boolean putData(String key,long value);
}
