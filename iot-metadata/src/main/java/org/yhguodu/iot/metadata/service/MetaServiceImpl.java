package org.yhguodu.iot.metadata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by yhguodu on 2017/10/23.
 */
@Service
public class MetaServiceImpl implements MetaService{

    @Autowired
    RedisTemplate<String,Long> redisTemplate;
    @Override
    public long getData(String key) {
       // return redisTemplate.opsForValue().get(key);
        redisTemplate.boundValueOps(key).increment(1L);
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean putData(String key,long value) {
        redisTemplate.opsForValue().set(key,value);
        return true;
    }
}
