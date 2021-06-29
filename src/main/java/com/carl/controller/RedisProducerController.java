package com.carl.controller;

import com.carl.config.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: Carl
 * @Date: 2021/06/29/17:09
 * @Description: 生产者
 */
@RestController
@RequestMapping("/producer")
public class RedisProducerController {
    @Autowired
    RedisClient client;

    private final static String SUCCESS = "success";
    private final static String MESSAGE = "testMQ";
    private final static List<String> list;

    static {
        list = Arrays.asList(new String[]{"Carl", "Newcastle", "Computer Science"});
    }

    /**
     * 发送消息
     * @return
     */
    @RequestMapping("/sendMsg")
    public String sendMsg() {
        for (String msg : list) {
            client.lpush(MESSAGE, msg);
        }
        return SUCCESS;
    }
}
