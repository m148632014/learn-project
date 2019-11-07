package org.mfm.learn.rabbitmq.config;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * RabbitMQ 信息配置[Exchange、RoutingKey、QueueName、ListennerConfig]
 *
 * @author MengFanmao
 * @since 2019年2月14日
 */
public class BizRabbitMQConfig {

    /**
     * 交换机 Exchange
     *
     * @author wangguangyi
     */
    public static class CalculateExchange {
        /**
         * 死信exchange
         */
        public static final String DEAD_EXCHANGE = "DLE.exchange";
        /**
         * direct类型exchange
         */
        public static final String KEPLER_EXCHANGE = "exchange.kepler.direct";

    }

    /**
     * 路由 RoutingKey
     *
     * @author wangguangyi
     */
    public static class CalculateRoutingKey {
        /**
         * 死信队列
         */
        public static final String DEAD_QUEUE_KEY = "DLQ_key_direct_kepler";
        /**
         * 批划计算通知key
         */
        public static final String DEDUCT_CALCULATE_NOTICE_KEY = "key_direct_deduct_calculate_notice";
        /**
         * 联合贷A批批划失败后批划计算key
         */
        public static final String UNION_A_FAILURE_DEDUCT_CALCULATE = "key_direct_union_a_deduct_failure_calculate";
        /**
         * 联合贷A批批划失败后批划计算结果key
         */
        public static final String UNION_A_FAILURE_DEDUCT_CALCULATE_RESULT = "key_direct_union_a_deduct_failure_calculate_result";
    }

    /**
     * 队列名称 QueueName
     *
     * @author wangguangyi
     */
    public static class CalculateQueueName {

        /**
         * 死信queue
         */
        public static final String DLQ_QUEUE_KEPLER = "DLQ.queue.kepler";
        /**
         * 批划计算通知队列
         */
        public static final String DEDUCT_CALCULATE_NOTICE_QUEUE = "queue.calculate.direct.deduct.calculate.notice";
        /**
         * 联合贷A批批划失败后批划队列
         */
        public static final String UNION_A_FAILURE_DEDUCT_CALCULATE_QUEUE = "queue.calculate.direct.unionAFailureDeductCalculate";
        /**
         * 联合贷A批批划失败后批划结果队列
         */
        public static final String UNION_A_FAILURE_DEDUCT_CALCULATE_RESULT_QUEUE = "queue.calculate.direct.unionAFailureDeductCalculate.result";

    }

    /**
     * 需要特别配置的监听
     * （如果需要特别配置concurrentConsumers和maxConcurrentConsumers）
     * 默认：
     * <li>concurrentConsumers =1
     * <li>maxConcurrentConsumers = 3
     *
     * @author FengQing
     * @date 2018年12月13日 下午2:06:47
     */
    public enum CalculateListennerConfig {
        /**
         * B批+中间期批划计算队列
         */
        BWITHMIDDLE_DEDUCT_CALCULATE_QUEUE(
                CalculateQueueName.UNION_A_FAILURE_DEDUCT_CALCULATE_QUEUE, 12,
                32);

        private final String queueName;
        private final int concurrentConsumers;
        private final int maxConcurrentConsumers;

        private CalculateListennerConfig(String queueName,
                int concurrentConsumers, int maxConcurrentConsumers) {
            this.queueName = queueName;
            this.concurrentConsumers = concurrentConsumers;
            this.maxConcurrentConsumers = maxConcurrentConsumers;
        }

        private CalculateListennerConfig(String queueName) {
            this.queueName = queueName;
            this.concurrentConsumers = 1;
            this.maxConcurrentConsumers = 3;
        }

        public String getQueueName() {
            return this.queueName;
        }

        public int getMaxConcurrentConsumers() {
            return this.maxConcurrentConsumers;
        }

        public int getConcurrentConsumers() {
            return this.concurrentConsumers;
        }

        @Override
        public String toString() {
            return this.queueName;
        }

        public static Map<String, CalculateListennerConfig> getAllMapping() {
            return Stream.of(CalculateListennerConfig.values())
                .collect(Collectors.toMap(i -> i.toString(), i -> i));
        }
    }
}
