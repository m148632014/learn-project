package org.mfm;

import lombok.Data;

/**
 * 每个服务可以有多个实例
 * ServiceInstance表示每个实例的基本信息
 *
 * @author MengFanmao
 * @since 2020年1月3日
 */
@Data
public class ServiceInstance {
    /**
     * 判断服务实例不在存活的周期
     */
    private static final Long NOT_ALIVE_PERIOD = 90 * 1000L;

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
    /**
     * 契约信息
     */
    private Lease lease;

    ServiceInstance() {
        this.lease = new Lease();
    }

    /**
     * 服务续约
     */
    public void renew() {
        this.lease.renew();
    }

    /**
     * 服务续约
     *
     * @return
     */
    public Boolean isAlive() {
        return this.lease.isAlive();
    }

    @Data
    private class Lease {

        /**
         * 最近一次心跳时间
         */
        private Long lastestHeartbeatTime = System.currentTimeMillis();

        /**
         * 服务实例是否存活
         *
         * @return
         */
        public Boolean isAlive() {
            Long currentTime = System.currentTimeMillis();
            if (currentTime
                - this.lastestHeartbeatTime > ServiceInstance.NOT_ALIVE_PERIOD) {
                System.out.println("服务实例【"
                    + ServiceInstance.this.serviceInstanceId + "】，不再存活，上次续约时间："
                    + this.lastestHeartbeatTime + "当前时间：" + currentTime);
                return false;
            }
            System.out.println(
                "服务实例【" + ServiceInstance.this.serviceInstanceId + "】，保持存活");
            return true;

        }

        /**
         * 续约
         */
        private void renew() {
            this.lastestHeartbeatTime = System.currentTimeMillis();
            System.out.println("服务实例【" + ServiceInstance.this.serviceInstanceId
                + "】，进行续约：" + this.lastestHeartbeatTime);
        }

    }

}
