package com.test.userservicetest.domain.util;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class StringRedisUtil {

    // 计数器String Redis模板
    @Resource StringRedisTemplate stringRedisTemplate;






    /**
     * 根据key获取过期时间
     * @param key 键 不能为null
     * @return 时间（秒） -1 - 永久有效； -2 - key为null
     */
    public long getExpire(String key) {
        if (key == null || key.equals("")) {
            return -2;
        }
        return stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
    }


    /**
     * 删除缓存
     * @param key 键 可以传一个，也可以传若干个
     */
    public void delete(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                stringRedisTemplate.delete(key[0]);
            } else {
                stringRedisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }


    /**
     * 获取缓存
     * @param key key
     * @return value
     */
    public String get(String key) {
        return key == null ? null : stringRedisTemplate.opsForValue().get(key);
    }


    /**
     * 存入缓存
     * @param key key
     * @param value value
     * @return result
     */
    public boolean set(String key, String value) {
        try {
            stringRedisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     * 计数器，自增1
     * @param key key
     */
    public void incrByOne(String key) {
        stringRedisTemplate.opsForValue().increment(key);
    }

}
