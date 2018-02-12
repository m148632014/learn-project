package org.mfm.learn.pattern.abstracts.command;

/**
 * 接收者执行与请求相关的操作，它具体实现对请求的业务处理。
 * <p>
 * 相当于 Service
 *
 * @author admin
 *
 */
public class Receiver {
	void action() {
		System.out.println("Receiver.action()");
	}
}
