package org.mfm.learn.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = { JmsAutoConfiguration.class })
@EnableEurekaClient
public class LearnRabbitMQApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(LearnRabbitMQApplication.class, args);
    }
}
