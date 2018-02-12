package org.mfm.learn.pattern.abstracts.observer;

public class ConcreteSubject extends Subject{
	 private String state;

    public String getState() {
        return this.state;
    }

    public void change(String newState){
        this.state = newState;
        System.out.println("主题状态为：" + this.state);
        //状态发生改变，通知各个观察者
        this.nodifyObservers(this.state);
    }
}
