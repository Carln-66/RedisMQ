package com.carl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: Carl
 * @Date: 2021/06/29/17:08
 * @Description:
 */
@Component
public class RedisClient {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**********************************消息队列*******************************************/

    /**
     * 向消息队列添加消息
     * @param key 键
     * @param value 值
     * @return 处理状态
     */
    public boolean lpush(String key, Object value) {
        try {
            redisTemplate.opsForList().leftPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 消息被取出
     * @param key 键
     * @return 消息
     */
    public Object rpop(String key) {
        try {
            return redisTemplate.opsForList().rightPop(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 阻塞式取消息
     * @param key 键
     * @param timeout 超时时间
     * @param timeUnit 时间单位
     * @return 消息
     */
    public Object brpop(String key, long timeout, TimeUnit timeUnit) {
        try {
            return redisTemplate.opsForList().rightPop(key, timeout, timeUnit);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查看消息
     * @param key 键
     * @param start 开始位置
     * @param end 结束位置（-1代表所有值）
     * @return 消息
     */
    public List<Object> lrange(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**********************************消息队列*******************************************/
}
