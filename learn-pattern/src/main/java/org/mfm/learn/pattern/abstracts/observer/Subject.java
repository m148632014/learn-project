package org.mfm.learn.pattern.abstracts.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    /**
     * 用来保存注册的观察者对象
     */
    private List<Observer> list = new ArrayList<Observer>();

    /**
     * 注册观察者对象
     * 
     * @param observer
     *        观察者对象
     */
    public void attach(Observer observer) {

        this.list.add(observer);
        System.out.println("Attached an observer");
    }

    /**
     * 删除观察者对象
     * 
     * @param observer
     *        观察者对象
     */
    public void detach(Observer observer) {

        this.list.remove(observer);
    }

    /**
     * 通知所有注册的观察者对象
     * 
     * @param newState
     */
    public void nodifyObservers(String newState) {

        for (Observer observer : this.list) {
            observer.update(newState);
        }
    }
}
