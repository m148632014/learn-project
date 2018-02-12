package org.mfm.learn.pattern.abstracts.decorator;

/**
 * 具体装饰类A 通过构造函数
 *
 * @author MengFanmao
 *
 */
public class ConcreteDecoratorA extends Decorator {
	public ConcreteDecoratorA(Component component) {
		super(component);
	}

	@Override
	public void operation() {
		super.operation();
		// 调用增强业务方法，对原有对象进行装饰、扩展、增强
		this.addedBehavior();
	}

	private void addedBehavior() {
		System.out.println("我是具体的装饰类A，我可以增强原有对象方法");
	}

}
