/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.designDataStructure;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: LoggerRateLimiter.java, v 0.1 2020-08-09 11:25 paras.chawla Exp $$
 */
public class LoggerRateLimiter {

    // K - Message , V - timestamp
    Map<String, Integer> map;

    /**
     * Initialize your val structure here.
     */
    public LoggerRateLimiter() {
        map = new HashMap<String, Integer>();
    }

    /**
     * Returns true if the message should be printed in the given timestamp, otherwise returns false. If this method returns false, the
     * message will not be printed. The timestamp is in seconds granularity.
     */
    public boolean shouldPrintMessage(int timestamp, String message) {

        // if not exist - print right away and add in map
        if (!map.containsKey(message)) {
            map.put(message, timestamp);
            return true;
        }
        // if map already contains message, check if timestamp past 10 or not
        else {
            int prevTimestamp = map.get(message);
            if (timestamp - prevTimestamp >= 10) {
                map.put(message, timestamp);
                return true;
            }
        }
        return false;
    }

    public boolean shouldPrintMessageRefactor(int timestamp, String message) {
        int lastStamp = map.getOrDefault(message, -10);
        if (timestamp - lastStamp >= 10) {
            map.put(message, timestamp);
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        LoggerRateLimiter obj = new LoggerRateLimiter();
        System.out.println(obj.shouldPrintMessage(1, "foo"));//true
        System.out.println(obj.shouldPrintMessage(2, "bar"));//true
        System.out.println(obj.shouldPrintMessage(3, "foo"));//false
        System.out.println(obj.shouldPrintMessage(8, "bar"));//false
        System.out.println(obj.shouldPrintMessage(10, "foo"));//false
        System.out.println(obj.shouldPrintMessage(11, "foo"));//true
    }

}