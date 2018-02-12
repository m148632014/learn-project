package org.mfm.learn;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mfm.learn.service.sender.QueueSender;
import org.mfm.learn.service.sender.TopicSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@ContextConfiguration("/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class QueueSenderTest {
	@Resource
	QueueSender queueSender;
	@Resource
	TopicSender topicSender;
	@Test
	public void testQueue() throws Exception {
		topicSender.send("test.topic", "MFM");
		queueSender.send("test.queue", "LKM");
	}
}
