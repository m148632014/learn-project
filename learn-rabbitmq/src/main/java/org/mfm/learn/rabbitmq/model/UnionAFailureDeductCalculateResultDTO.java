package org.mfm.learn.rabbitmq.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author MengFanmao
 * @since 2019年1月16日
 */
@Data
public class UnionAFailureDeductCalculateResultDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 批划计算结果元数据
     */
    private DeductCalculateResultMetaDTO deductCalculateResultMeta;

    private List<UnionAFailureDeductCalculateResultDetailDTO> details;

}
