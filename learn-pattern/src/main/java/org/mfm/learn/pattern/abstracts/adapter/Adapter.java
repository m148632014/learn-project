package org.mfm.learn.pattern.abstracts.adapter;

public class Adapter extends Target {
	private Adaptee adaptee;

	public Adapter(Adaptee adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void request() {
		this.adaptee.sepcificRequest();
	}
}
