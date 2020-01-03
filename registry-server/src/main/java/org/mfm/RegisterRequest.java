package org.mfm;

import lombok.Data;

@Data
public class RegisterRequest {
    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * 服务实例Id
     */
    private String serviceInstanceId;
    /**
     * 实例ip
     */
    private String ip;
    /**
     * 实例端口
     */
    private Integer port;
    /**
     * 实例所在主机名
     */
    private String hostname;
}
