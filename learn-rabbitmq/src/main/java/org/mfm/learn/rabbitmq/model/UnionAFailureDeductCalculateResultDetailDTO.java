package org.mfm.learn.rabbitmq.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @author MengFanmao
 * @since 2019年1月16日
 */
@Data
public class UnionAFailureDeductCalculateResultDetailDTO
        implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 进件号
     */
    private Integer relationId;
    /**
     * 联合贷批划金额
     */
    private BigDecimal unionDeductTotal;
    /**
     * 当期期数
     */
    private Integer currentPhase;
    /**
     * 当期还款日
     */
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING,
            timezone = "GMT+08")
    private Date repayDay;

}
