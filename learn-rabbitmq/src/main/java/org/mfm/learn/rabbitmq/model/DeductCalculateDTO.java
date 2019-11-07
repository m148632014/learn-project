package org.mfm.learn.rabbitmq.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.mfm.learn.rabbitmq.model.enums.DeductCalculateNoticeType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @author MengFanmao
 * @since 2019年1月16日
 */
@Data
public class DeductCalculateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Integer> relaitionIds;
    /**
     * 计算总数
     */
    private Integer totalCount;

    /**
     * 批划查询条件，某个还款日
     */
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING,
            timezone = "GMT+08")
    private Date deductDay;

    /**
     * 批次类型，根据批划类型选择不同的查询方式和发送到不同的计算队列
     */
    private DeductCalculateNoticeType deductCalculateNoticeType;
    /**
     * 批划批次流水号
     */
    private String externalRefNumber;

}
