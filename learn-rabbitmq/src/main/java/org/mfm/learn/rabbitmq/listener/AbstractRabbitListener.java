/**
 * Copyright(c) 2011-2017 by YouCredit Inc.
 * All Rights Reserved
 */
package org.mfm.learn.rabbitmq.listener;

import java.io.Serializable;
import java.util.Map;

import javax.jms.JMSException;

import org.mfm.learn.rabbitmq.utils.JsonMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Value;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
public abstract class AbstractRabbitListener<T extends Serializable>
        implements MessageListener {
    protected static final String MESSAGE_RETRY_COUNT = "MESSAGE_RETRY_COUNT";
    /**
     * 最大重试次数
     */
    @Value("${spring.rabbitmq.listener.simple.retry.max-attempts}")
    private int maxAttempts;

    /**
     * Json解析器
     * <p>
     * 允许使用jsonview
     */
    protected static final JsonMapper JSON_MAPPER = JsonMapper
        .nonEmptyMapperWithView();

    /**
     * 当前Listener处理的类
     */
    private Class<T> claz;

    /**
     *
     */
    @Override
    public void onMessage(Message message) {
        try {
            T param = this.parseMessage(message);
            // 解析参数，放到监控日志中
            // 执行实际的操作
            this.dealMessage(param);
        } catch (Exception e) {
            Map<String, Object> header = message.getMessageProperties()
                .getHeaders();
            AbstractRabbitListener.setCount(header);
            if ((int) header.get(
                AbstractRabbitListener.MESSAGE_RETRY_COUNT) >= this.maxAttempts) {
                AbstractRabbitListener.log.error(
                    "【RabbitMQ】【消息处理】【异常】 请查找原因&补发，详细信息见死信队列。Message={}",
                    message, e);
            } else {
                AbstractRabbitListener.log.warn("【RabbitMQ】【发生异常】【重试】", e);
            }
            throw AbstractRabbitListener.transIntoRuntimeException(e);
        } finally {
            AbstractRabbitListener.log.info("【RabbitMQ】【消息处理】【完成】");
        }
    }

    /**
     * 计数器：在message的header里记录重试次数。
     *
     * @author zhangwentao
     * @Since 下午4:20:02
     * @param header
     *        void
     */
    private static void setCount(Map<String, Object> header) {
        if (header.get(AbstractRabbitListener.MESSAGE_RETRY_COUNT) == null) {
            header.put(AbstractRabbitListener.MESSAGE_RETRY_COUNT, 1);
        } else {
            header.put(AbstractRabbitListener.MESSAGE_RETRY_COUNT,
                (int) header.get(AbstractRabbitListener.MESSAGE_RETRY_COUNT)
                    + 1);
        }
    }

    /**
     * 消息转换
     *
     * @param message
     * @return
     * @throws JMSException
     */
    private T parseMessage(Message message) {
        T actualMessage = null;
        String contentType = message.getMessageProperties().getContentType();
        if (contentType != null && !contentType
            .equals(MessageProperties.CONTENT_TYPE_SERIALIZED_OBJECT)) {
            if (this.claz.isAssignableFrom(Message.class)) {
                //处理死信队列
                actualMessage = (T) message;
            } else {
                //处理普通队列json
                String json = new String(message.getBody());
                AbstractRabbitListener.log.info(
                    "[2].【RabbitMQ】【消息解析】[Class={}],[TextMessage is={}]",
                    this.getClass().getName(), json);
                actualMessage = AbstractRabbitListener.JSON_MAPPER
                    .fromJson(json, this.claz);
            }
        } else {
            // 非死信队列、非json 无法处理，则直接丢出异常
            AbstractRabbitListener.log.error(
                "【RabbitMQ】【消息解析】【失败】无法处理此[{}]类型的消息！[Msg={}]，[Info={}]",
                new String(message.getBody()),
                message.getMessageProperties().getContentType(), message);
        }
        return actualMessage;
    }

    /**
     * 转换为RuntimeException
     *
     * @param throwable
     * @return
     */
    private static RuntimeException transIntoRuntimeException(
            Throwable throwable) {
        return throwable instanceof RuntimeException
            ? (RuntimeException) throwable
            : new RuntimeException(throwable);
    }

    /**
     * 实际的业务逻辑处理
     *
     * @param param
     */
    protected abstract void dealMessage(T param) throws Exception;

}
