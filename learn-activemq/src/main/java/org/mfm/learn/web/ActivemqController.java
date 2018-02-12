package org.mfm.learn.web;


import javax.annotation.Resource;

import org.mfm.learn.service.sender.QueueSender;
import org.mfm.learn.service.sender.TopicSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ActivemqController {
	@Resource
	QueueSender queueSender;
	@Resource
	TopicSender topicSender;
	
	@ResponseBody
	@RequestMapping("/ok")
	public String topicSender(String message) {
		queueSender.send("test.queue", message);
		topicSender.send("test.topic", message);
		return "SUCCESS";
	}
}