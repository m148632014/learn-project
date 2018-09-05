package org.mfm.learn;



import javax.annotation.Resource;

import org.apache.activemq.junit.EmbeddedActiveMQBroker;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * ReserveListener 集成测试
 *
 * @author MengFanmao
 * @since 2018年7月27日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext
public class ListenerTest {

    @ClassRule
    public static EmbeddedActiveMQBroker broker = new EmbeddedActiveMQBroker();

    @Resource
    private JmsTemplate jmsTemplate;

    /**
     * 集成测试
     *
     * @author MengFanmao
     * @throws JsonProcessingException 
     * @since 2018年7月30日
     */
    @Test
    public void testSend() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValueAsString(new String("aa"));
//        this.jmsTemplate.convertAndSend(this.callerReceiverPublishQueue,
//            jmsMessage);
    }
}
