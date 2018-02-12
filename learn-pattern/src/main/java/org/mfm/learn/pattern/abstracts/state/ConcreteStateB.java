package org.mfm.learn.pattern.abstracts.state;

public class ConcreteStateB implements State {
	private static ConcreteStateB single = null;

	private ConcreteStateB() {
	}

	// 静态工厂方法
	public static ConcreteStateB getInstance() {
		if (ConcreteStateB.single == null) {
			ConcreteStateB.single = new ConcreteStateB();
		}
		return ConcreteStateB.single;
	}

	public void handle(Context context) {
		System.out.println("doing something in State B. done,change state to A");
		context.changeState(ConcreteStateA.getInstance());

	}

}
