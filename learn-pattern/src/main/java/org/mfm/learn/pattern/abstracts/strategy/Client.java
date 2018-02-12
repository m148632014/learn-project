package org.mfm.learn.pattern.abstracts.strategy;

public class Client {
	public static void main(String[] args) {
		Strategy s1 = new ConcreteStrategyA();
		Context cxt = new Context();
		cxt.setStrategy(s1);
		cxt.algorithm();

		Strategy s2 = new ConcreteStrategyB();
		cxt.setStrategy(s2);
		cxt.algorithm();
	}
}
