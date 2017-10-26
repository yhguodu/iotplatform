package org.yhguodu.iot.metadata.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.yhguodu.iot.common.metadata.MetaService;
import org.yhguodu.iot.common.service.RpcService;

/**
 * Created by yhguodu on 2017/10/23.
 */
@RpcService(MetaService.class)
public class MetaServiceImpl implements MetaService {

    private static final Logger logger = LoggerFactory.getLogger(MetaServiceImpl.class);
    @Autowired
    RedisTemplate<String,Long> redisTemplate;
    @Override
    public long getData(String key) {
       // return redisTemplate.opsForValue().get(key);
        logger.info("getData {}",key);
        redisTemplate.boundValueOps(key).increment(1L);
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean putData(String key,long value) {
        redisTemplate.opsForValue().set(key,value);
        return true;
    }
    
}
