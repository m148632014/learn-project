package org.mfm.learn.pattern.abstracts.strategy;

public class Context {
	private Strategy strategy;

	public void algorithm() {
		this.strategy.algorithm();
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

}
