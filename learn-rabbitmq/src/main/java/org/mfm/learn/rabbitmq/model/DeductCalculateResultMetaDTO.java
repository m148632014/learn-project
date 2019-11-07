package org.mfm.learn.rabbitmq.model;

import java.io.Serializable;

import org.mfm.learn.rabbitmq.model.enums.DeductCalculateResultCode;

import lombok.Data;

/**
 * @author MengFanmao
 * @since 2019年1月16日
 */
@Data
public class DeductCalculateResultMetaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 应计算个数
     */
    private Integer commitCount;
    /**
     * 实际计算个数
     */
    private Integer actualCount;
    /**
     * 计算总数
     */
    private Integer totalCount;
    /**
     * 批划批次流水号
     */
    private String externalRefNumber;
    /**
     * 批划类型
     */
    private String deductCalculateNoticeType;
    /**
     * 批划结算结果状态
     */
    private DeductCalculateResultCode code;

}
