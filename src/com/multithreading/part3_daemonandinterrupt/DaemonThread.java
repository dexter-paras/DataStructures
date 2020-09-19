/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.multithreading.part3_daemonandinterrupt;

/**
 * @author paras.chawla
 * @version $Id: DaemonThread.java, v 0.1 2020-08-25 00:16 paras.chawla Exp $$
 */
// Background threads that don't prevent the application from existing if main thread terminates
// Scenario 1 = Background task which aren't focused
// Scenario 2 =Text editor background task saving file after 1 sec shouldn't worry
// As soon as main thread ended, if other threads are daemon thread then application would close gracefully

public class DaemonThread {
}