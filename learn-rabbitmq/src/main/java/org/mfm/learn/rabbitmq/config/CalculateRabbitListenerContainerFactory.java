package org.mfm.learn.rabbitmq.config;

import java.util.Map;
import java.util.stream.Stream;

import org.apache.commons.lang.ArrayUtils;
import org.mfm.learn.rabbitmq.config.BizRabbitMQConfig.CalculateListennerConfig;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

import lombok.extern.slf4j.Slf4j;

/**
 * rabbitMQ 消费监听工厂，主要负责设置并发
 *
 * @author MengFanmao
 * @since 2019年6月10日
 */
@Slf4j
public class CalculateRabbitListenerContainerFactory
        extends SimpleRabbitListenerContainerFactory {

    @Override
    protected void initializeContainer(
            SimpleMessageListenerContainer instance) {

        super.initializeContainer(instance);

        Map<String, CalculateListennerConfig> mapping = CalculateListennerConfig
            .getAllMapping();

        if (ArrayUtils.isNotEmpty(instance.getQueueNames())) {
            Stream.of(instance.getQueueNames()).forEach(q -> {

                CalculateListennerConfig config = mapping.get(q);

                if (config != null) {
                    instance.setMaxConcurrentConsumers(
                        config.getMaxConcurrentConsumers());
                    instance.setConcurrentConsumers(
                        config.getConcurrentConsumers());
                }
            });
        }
        CalculateRabbitListenerContainerFactory.log
            .info("【KEP-678】【Listener 配置】:{}", instance);

    }
}
