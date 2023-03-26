package com.cxm.cxmmusic.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author
 * @create 2023-03-18 19:39
 */
@Component
public class RedisUtils {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public <T> T getValue(String key,Class<T> clazz) {
        String value = stringRedisTemplate.opsForValue().get(key);
        return JSON.parseObject(value, clazz);
    }

    public <T> void setValue(String key, T value) {

        String jsonString = JSON.toJSONString(value);

        stringRedisTemplate.opsForValue().set(key,jsonString);
    }

    public <T> void setValue(String key, T value, Integer timeout, TimeUnit unit) {

        String jsonString = JSON.toJSONString(value);

        stringRedisTemplate.opsForValue().set(key, jsonString, timeout, unit);
    }

    public Boolean deleteValue(String key){
        return stringRedisTemplate.delete(key);
    }
}
