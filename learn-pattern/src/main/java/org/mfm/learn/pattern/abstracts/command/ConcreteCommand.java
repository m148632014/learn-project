package org.mfm.learn.pattern.abstracts.command;

/**
 * 操作 具体命令类是抽象命令类的子类，实现了在抽象命令类中声明的方法，它对应具体的接收者对象，将接收者对象的动作绑定其中；
 * <p>
 * 绑定了Service
 *
 * @author admin
 *
 */
public class ConcreteCommand implements Command {

	private Receiver receiver;

	/**
	 * 命令使用接收者，new ConcreteCommand(new Receiver())构造方法只是一种方式而已，也可以用属性注入，set方法等使用
	 * 
	 * @param receiver
	 */
	public ConcreteCommand(Receiver receiver) {
		this.receiver = receiver;
	}

	public void execute() {
		System.out.println("ConcreteCommand.execute()");
		this.receiver.action();
	}

}
