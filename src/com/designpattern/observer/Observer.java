/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.designpattern.observer;

/**
 * @author paras.chawla
 * @version $Id: Observer.java, v 0.1 2019-11-03 12:18 paras.chawla Exp $$
 */

/* Observer / Subscriber
  Implemented by all observers that are to be updated
  whenever there is an update from CricketData
 *
 * Issues with Observer Design Pattern
 * Lapsed listener problem:The leak happens when an observer fails to unsubscribe
 * from the subject when it no longer needs to listen. Consequently, the subject
 * still holds a reference to the observer which prevents it from being garbage
 * collected — including all other objects it is referring to — for as long as
 * the subject is alive, which could be until the end of the application
 *
 * This causes not only a memory leak, but also a performance degradation with
 * an "uninterested" observer receiving and acting on unwanted events.
 * This can be prevented by the subject holding weak references to the observers,
 * allowing them to be garbage collected as normal without needing to be unregistered
 */
public interface Observer {

    void update(int runs, int wickets, float overs);
}