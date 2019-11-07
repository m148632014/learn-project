package org.mfm.learn.rabbitmq.listener;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.mfm.learn.rabbitmq.config.BizRabbitMQConfig;
import org.mfm.learn.rabbitmq.config.BizRabbitMQConfig.CalculateRoutingKey;
import org.mfm.learn.rabbitmq.model.DeductCalculateDTO;
import org.mfm.learn.rabbitmq.model.DeductCalculateResultMetaDTO;
import org.mfm.learn.rabbitmq.model.UnionAFailureDeductCalculateResultDTO;
import org.mfm.learn.rabbitmq.model.UnionAFailureDeductCalculateResultDetailDTO;
import org.mfm.learn.rabbitmq.model.enums.DeductCalculateResultCode;
import org.mfm.learn.rabbitmq.service.RabbitMqService;
import org.mfm.learn.rabbitmq.utils.JsonMapper;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UnionAFailureDeductCalculateListener
        extends AbstractRabbitListener<DeductCalculateDTO> {

    @Resource
    private RabbitMqService rabbitMqService;

    public UnionAFailureDeductCalculateListener() {
        this.setClaz(DeductCalculateDTO.class);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(
            value = BizRabbitMQConfig.CalculateQueueName.UNION_A_FAILURE_DEDUCT_CALCULATE_QUEUE,
            durable = "true", autoDelete = "false", exclusive = "false"),
            exchange = @Exchange(
                    value = BizRabbitMQConfig.CalculateExchange.KEPLER_EXCHANGE,
                    durable = "true", autoDelete = "false",
                    type = ExchangeTypes.DIRECT),
            key = BizRabbitMQConfig.CalculateRoutingKey.UNION_A_FAILURE_DEDUCT_CALCULATE))
    public void handle(Message message) {
        super.onMessage(message);
    }

    /**
     * 计算当期批划数据并且发送到mq
     *
     * @author MengFanmao
     * @since 2019年2月13日
     * @param deductCalculateDTO
     * @param metadata
     */
    @Override
    protected void dealMessage(DeductCalculateDTO deductCalculateDTO) {
        //计算具体批划数据
        UnionAFailureDeductCalculateResultDTO result = UnionAFailureDeductCalculateListener
            .calculateDetails(deductCalculateDTO);
        //发送结果到队列
        String message = JsonMapper.nonEmptyMapper().toJson(result);
        UnionAFailureDeductCalculateListener.log.info("联合贷批划计算结果，jmsMessage={}",
            message);
        this.rabbitMqService.send2Rabbit(result,
            CalculateRoutingKey.UNION_A_FAILURE_DEDUCT_CALCULATE_RESULT);
    }

    /**
     * 计算具体当期批划数据
     *
     * @author MengFanmao
     * @since 2019年2月13日
     * @param deductCalculateDTO
     * @param metadata
     * @return
     */
    private static UnionAFailureDeductCalculateResultDTO calculateDetails(
            DeductCalculateDTO deductCalculateDTO) {
        UnionAFailureDeductCalculateResultDTO result = new UnionAFailureDeductCalculateResultDTO();
        DeductCalculateResultMetaDTO metadata = new DeductCalculateResultMetaDTO();
        List<UnionAFailureDeductCalculateResultDetailDTO> details = Collections.EMPTY_LIST;
        metadata.setDeductCalculateNoticeType(
            deductCalculateDTO.getDeductCalculateNoticeType().name());
        metadata
            .setExternalRefNumber(deductCalculateDTO.getExternalRefNumber());
        metadata.setCode(DeductCalculateResultCode.NODATA);
        metadata.setCommitCount(0);
        metadata.setActualCount(0);
        metadata.setTotalCount(0);
        result.setDetails(details);
        result.setDeductCalculateResultMeta(metadata);
        return result;
    }

}