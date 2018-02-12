package org.mfm.learn.pattern.abstracts.command;

/**
 * 调用者即请求的发送者，又称为请求者，它通过命令对象来执行请求；
 * <p>
 * * 命令的使用者：可以执行多种命令，分发等
 * 
 * @author admin
 *
 */
public class Invoker {
	Command concreteCommand;

	public Invoker(Command command) {
		this.concreteCommand = command;
	}

	public void call() {
		System.out.println("Invoker.call()");
		this.concreteCommand.execute();
	}

}
