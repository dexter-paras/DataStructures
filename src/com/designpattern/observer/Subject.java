/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.designpattern.observer;

/**
 * @author paras.chawla
 * @version $Id: Subject.java, v 0.1 2019-11-03 12:19 paras.chawla Exp $$
 * <p>
 * References:
 * https://www.geeksforgeeks.org/observer-pattern-set-1-introduction/
 * https://www.geeksforgeeks.org/observer-pattern-set-2-introduction/
 * https://en.wikipedia.org/wiki/Lapsed_listener_problem
 */
public interface Subject {

    void registerObserver(Observer observer);

    void unregisterObserver(Observer observer);

    void notifyObservers();

}