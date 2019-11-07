package org.mfm.learn.rabbitmq.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.mfm.learn.rabbitmq.config.BizRabbitMQConfig.CalculateExchange;
import org.mfm.learn.rabbitmq.config.BizRabbitMQConfig.CalculateRoutingKey;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;

/**
 * 消息重试失败后的处理类。
 * 失败后往死信队列发送消息
 *
 * @author FengQing
 * @date 2018年12月19日 下午1:49:29
 */
public class CalculateRepublishMessageRecoverer
        extends RepublishMessageRecoverer {

    public CalculateRepublishMessageRecoverer(AmqpTemplate errorTemplate) {
        super(errorTemplate, CalculateExchange.DEAD_EXCHANGE,
            CalculateRoutingKey.DEAD_QUEUE_KEY);
    }

    @Override
    protected Map<? extends String, ? extends Object> additionalHeaders(
            Message message, Throwable cause) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("DateTime",
            DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        headers.put("Queue", message.getMessageProperties().getConsumerQueue());
        return headers;
    }
}
