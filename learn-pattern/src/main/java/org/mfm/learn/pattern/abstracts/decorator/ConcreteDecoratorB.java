package org.mfm.learn.pattern.abstracts.decorator;
/***
 * ContreteDecoratorB
 * ConcreteDecoratorB
 * @author admin
 *
 */
public class ConcreteDecoratorB extends Decorator {

	public ConcreteDecoratorB(Component component) {
		super(component);
	}

	@Override
	public void operation() {
		super.operation();
		// 调用增强业务方法，对原有对象进行装饰、扩展、增强
		this.addedOtherBehavior();
	}

	private void addedOtherBehavior() {
		System.out.println("我是具体的装饰类B，我也可以增强原有对象方法");
	}
}