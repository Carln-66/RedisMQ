package com.carl.controller;

import com.carl.config.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: Carl
 * @Date: 2021/06/29/17:09
 * @Description: 消费者
 */
@RestController
@RequestMapping("/consumer")
public class RedisConsumerController {
    @Autowired
    RedisClient client;

    private static final String MESSAGE = "testMQ";

    /**
     * 接收消息
     * @return 消息
     */
    @RequestMapping("/receiveMsg")
    public String receiveMsg() {
        return (String) client.brpop(MESSAGE, 0, TimeUnit.SECONDS);
    }
}
