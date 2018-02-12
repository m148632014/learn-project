package org.mfm.learn.pattern.abstracts.command;

/**
 * 客户端使用发送者Invoker执行命令
 *
 * @author admin
 *
 */
public class Client {
	public static void main(String[] args) {
		// 命令使用接收者，new ConcreteCommand(new
		// Receiver())构造方法只是一种方式而已，也可以用属性注入，set方法等使用
		Invoker invoker = new Invoker(new ConcreteCommand(new Receiver()));
		invoker.call();
	}
}
