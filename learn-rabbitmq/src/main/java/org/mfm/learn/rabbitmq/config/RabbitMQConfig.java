/**
 * Copyright(c) 2011-2018 by YouCredit Inc.
 * All Rights Reserved
 */
package org.mfm.learn.rabbitmq.config;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory.CacheMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author MengFanmao
 * @since 2019年2月13日
 */
@EnableRabbit
@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.addresses}")
    private String addresses;
    @Value("${spring.rabbitmq.requested-heartbeat}")
    private int requestedHeartbeat;
    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.cache.channel.size}")
    private int channelSize;

    /**
     * 因为使用了自定义的ConnectionFactory,所以需要定义RabbitAdmin
     *
     * @param producerRabbitConnectionFactory
     * @return
     */
    @Bean
    public RabbitAdmin keplerRabbitAdmin(
            ConnectionFactory producerRabbitConnectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(
            producerRabbitConnectionFactory);
        return rabbitAdmin;
    }

    /**
     * rabbit的生产者配置链接信息
     *
     * @param producerRabbitExecutor
     * @return
     */
    @Bean
    public ConnectionFactory producerRabbitConnectionFactory(
            ThreadPoolTaskExecutor producerRabbitExecutor) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(this.addresses);
        connectionFactory.setConnectionTimeout(30000);
        connectionFactory.setUsername(this.username);
        connectionFactory.setPassword(this.password);
        connectionFactory.setVirtualHost(this.virtualHost);
        connectionFactory.setRequestedHeartBeat(this.requestedHeartbeat);//心跳监测间隔

        //如果达到了限制,调用线程将会阻塞，直到某个通道可用或者超时, 在后者的情况中，将抛出 AmqpTimeoutException异常
        connectionFactory.setChannelCacheSize(this.channelSize);
        connectionFactory.setExecutor(producerRabbitExecutor);

        //其缓存模式为通道缓存
        connectionFactory.setCacheMode(CacheMode.CHANNEL);
        return connectionFactory;
    }

    /**
     * rabbit的消费者配置链接信息
     *
     * @param consumerRabbitExecutor
     * @return
     */
    @Bean
    public ConnectionFactory consumerRabbitConnectionFactory(
            ThreadPoolTaskExecutor consumerRabbitExecutor) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(this.addresses);
        connectionFactory.setConnectionTimeout(30000);
        connectionFactory.setUsername(this.username);
        connectionFactory.setPassword(this.password);
        connectionFactory.setVirtualHost(this.virtualHost);
        connectionFactory.setRequestedHeartBeat(this.requestedHeartbeat);//心跳监测间隔

        //如果达到了限制,调用线程将会阻塞，直到某个通道可用或者超时, 在后者的情况中，将抛出 AmqpTimeoutException异常
        connectionFactory.setChannelCacheSize(this.channelSize);
        connectionFactory.setExecutor(consumerRabbitExecutor);

        //其缓存模式为通道缓存
        connectionFactory.setCacheMode(CacheMode.CHANNEL);
        return connectionFactory;
    }

    /**
     * rabbitListenerContainerFactory <br/>
     * 作用：采用自定义工厂，覆盖默认的工厂，设置并发，设置Executor。
     *
     * @author FengQing
     * @date 2018年12月13日 下午2:19:19
     * @param configurer
     * @param consumerRabbitConnectionFactory
     * @param consumerRabbitExecutor
     * @return
     */
    @Bean
    public CalculateRabbitListenerContainerFactory rabbitListenerContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            ConnectionFactory consumerRabbitConnectionFactory,
            ThreadPoolTaskExecutor consumerRabbitExecutor) {
        CalculateRabbitListenerContainerFactory factory = new CalculateRabbitListenerContainerFactory();
        factory.setTaskExecutor(consumerRabbitExecutor);
        factory.setChannelTransacted(true);
        configurer.configure(factory, consumerRabbitConnectionFactory);
        return factory;
    }

    /**
     * 生产者线程池
     *
     * @return ThreadPoolTaskExecutor
     */
    @Bean
    public ThreadPoolTaskExecutor producerRabbitExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setKeepAliveSeconds(60);//允许的空闲时间
        //这个策略重试添加当前的任务，他会自动重复调用 execute() 方法，直到成功
        executor.setRejectedExecutionHandler(
            new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setQueueCapacity(2000);//缓存队列
        executor.setCorePoolSize(50);//线程池维护线程的最少数量
        executor.setMaxPoolSize(500);
        return executor;
    }

    /**
     * 消费者线程池
     * CorePoolSize:200，每个listener默认是1-3个线程，启动至少需要1个
     * cn.youcredit.kepler.calculate.jms.config.BizRabbitMQConfig.CalculateListennerConfig.concurrentConsumers个数当超过CorePoolSize
     * 时，启动检查等待60秒后会报错，见{@link org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer.AsyncMessageProcessingConsumer#getStartupException}
     * 会出现RabbitMQ消息无法被消费的情况
     * 
     * @return ThreadPoolTaskExecutor
     */
    @Bean
    public ThreadPoolTaskExecutor consumerRabbitExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setKeepAliveSeconds(60);//允许的空闲时间
        //这个策略重试添加当前的任务，他会自动重复调用 execute() 方法，直到成功
        executor.setRejectedExecutionHandler(
            new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setQueueCapacity(2000);//缓存队列
        executor.setCorePoolSize(50);//线程池维护线程的最少数量
        executor.setMaxPoolSize(500);
        return executor;
    }

    /**
     * 配置：Rabbit发送消息的模板 RabbitTemplate
     *
     * @param producerRabbitConnectionFactory
     * @param retryTemplate
     * @return RabbitTemplate
     */
    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory producerRabbitConnectionFactory,
            RetryTemplate retryTemplate) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(producerRabbitConnectionFactory);
        rabbitTemplate.setChannelTransacted(true);
        rabbitTemplate.setRetryTemplate(retryTemplate);
        return rabbitTemplate;
    }

    /**
     * messageRecoverer <br/>
     * 作用：listener 重试失败后的恢复策略。
     *
     * @author FengQing
     * @date 2018年12月19日 下午3:39:04
     * @param rabbitTemplate
     * @return
     */
    @Bean
    public MessageRecoverer messageRecoverer(RabbitTemplate rabbitTemplate) {
        return new CalculateRepublishMessageRecoverer(rabbitTemplate);
    }
}
