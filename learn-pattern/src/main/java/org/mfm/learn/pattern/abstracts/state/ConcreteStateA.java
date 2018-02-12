package org.mfm.learn.pattern.abstracts.state;

public class ConcreteStateA implements State {
	private static ConcreteStateA single = null;

	private ConcreteStateA() {
	}

	// 静态工厂方法
	public static ConcreteStateA getInstance() {
		if (ConcreteStateA.single == null) {
			ConcreteStateA.single = new ConcreteStateA();
		}
		return ConcreteStateA.single;
	}

	public void handle(Context context) {
		System.out.println("doing something in State A. done,change state to B");
		context.changeState(ConcreteStateB.getInstance());
	}

}
