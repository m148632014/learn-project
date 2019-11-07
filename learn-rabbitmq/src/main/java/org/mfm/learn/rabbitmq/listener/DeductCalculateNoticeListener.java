package org.mfm.learn.rabbitmq.listener;

import java.util.Collections;

import javax.annotation.Resource;

import org.mfm.learn.rabbitmq.config.BizRabbitMQConfig;
import org.mfm.learn.rabbitmq.config.BizRabbitMQConfig.CalculateRoutingKey;
import org.mfm.learn.rabbitmq.model.DeductCalculateDTO;
import org.mfm.learn.rabbitmq.model.DeductCalculateNoticeDTO;
import org.mfm.learn.rabbitmq.service.RabbitMqService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 批划计算通知监听器
 *
 * @author MengFanmao
 * @since 2019年2月13日
 */
@Slf4j
@Component
public class DeductCalculateNoticeListener
        extends AbstractRabbitListener<DeductCalculateNoticeDTO> {

    @Resource
    private RabbitMqService rabbitMqService;

    public DeductCalculateNoticeListener() {
        this.setClaz(DeductCalculateNoticeDTO.class);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(
            value = BizRabbitMQConfig.CalculateQueueName.DEDUCT_CALCULATE_NOTICE_QUEUE,
            durable = "true", autoDelete = "false", exclusive = "false"),
            exchange = @Exchange(
                    value = BizRabbitMQConfig.CalculateExchange.KEPLER_EXCHANGE,
                    durable = "true", autoDelete = "false",
                    type = ExchangeTypes.DIRECT),
            key = BizRabbitMQConfig.CalculateRoutingKey.DEDUCT_CALCULATE_NOTICE_KEY))
    public void handle(Message message) {
        super.onMessage(message);
    }

    @Override
    protected void dealMessage(DeductCalculateNoticeDTO noticeDTO) {
        switch (noticeDTO.getDeductCalculateNoticeType()) {
            case UnionAFailureDeduct:
                DeductCalculateDTO deductCalculateDTO = new DeductCalculateDTO();
                deductCalculateDTO.setRelaitionIds(Collections.EMPTY_LIST);
                deductCalculateDTO.setTotalCount(0);
                deductCalculateDTO.setDeductCalculateNoticeType(
                    noticeDTO.getDeductCalculateNoticeType());
                deductCalculateDTO
                    .setExternalRefNumber(noticeDTO.getExternalRefNumber());
                deductCalculateDTO.setDeductDay(noticeDTO.getDeductDay());
                DeductCalculateNoticeListener.log.info("无批划需要试算进件号",
                    AbstractRabbitListener.JSON_MAPPER
                        .toJson(deductCalculateDTO));
                this.rabbitMqService.send2Rabbit(deductCalculateDTO,
                    CalculateRoutingKey.UNION_A_FAILURE_DEDUCT_CALCULATE);
                break;
            default:

        }

        return;

    }

}