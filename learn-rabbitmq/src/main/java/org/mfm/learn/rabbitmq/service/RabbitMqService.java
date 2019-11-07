/**
 * Copyright(c) 2011-2018 by YouCredit Inc.
 * All Rights Reserved
 */
package org.mfm.learn.rabbitmq.service;

import javax.annotation.Resource;

import org.mfm.learn.rabbitmq.config.BizRabbitMQConfig.CalculateExchange;
import org.mfm.learn.rabbitmq.utils.JsonMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RabbitMqService {
    /**
     * Json解析器
     */
    protected static final JsonMapper JSON_MAPPER = JsonMapper.nonEmptyMapper();
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 将消息 发往account系统的异步队列来处理
     *
     * @param obj
     * @param key
     */
    public void send2Rabbit(Object obj, String key) {
        String message = RabbitMqService.JSON_MAPPER.toJson(obj);
        RabbitMqService.log.info("生成放入rabbitmq 的消息key={}.jmsMessage={}", key,
            message);
        this.rabbitTemplate.convertAndSend(CalculateExchange.KEPLER_EXCHANGE,
            key, message);
    }

}
