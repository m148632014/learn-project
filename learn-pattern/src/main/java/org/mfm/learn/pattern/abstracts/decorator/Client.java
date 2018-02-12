package org.mfm.learn.pattern.abstracts.decorator;

/**
 * 具体构件类 Component 比如某个service的接口，抽象类均算抽象构件类 <br>
 * 抽象构件类 ConcreteComponent 比如某个service的接口，抽象类均算抽象构件类 <br>
 *
 * @author MengFanmao
 *
 */
public class Client {
	public static void main(String[] args) {
		Component component, decoratorA;
		component = new ConcreteComponent();

		decoratorA = new ConcreteDecoratorA(component);
		// 对原有具体构件类ConcreteComponent的增强行为
		decoratorA.operation();

		System.out.println("---------------------------\n");

		System.out.println("---对原有构件对象进行 decoratorA、decoratorB 二次装饰---");
		// 如果需要对原有构件对象装饰后的decoratorA对象上进行再次装饰
		Component decoratorB;
		decoratorB = new ConcreteDecoratorB(decoratorA);
		decoratorB.operation();
	}
}
