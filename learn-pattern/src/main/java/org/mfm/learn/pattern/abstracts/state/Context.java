package org.mfm.learn.pattern.abstracts.state;

public class Context {
	private State state;

	public Context() {
		// default state is a
		this.state = ConcreteStateA.getInstance();
	}

	void changeState(State state) {
		this.state = state;
	}

	void request() {
		this.state.handle(this);
	}
}
