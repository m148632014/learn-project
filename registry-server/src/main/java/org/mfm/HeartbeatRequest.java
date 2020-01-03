package org.mfm;

import lombok.Data;

@Data
public class HeartbeatRequest {
    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * 服务实例Id
     */
    private String serviceInstanceId;
}
