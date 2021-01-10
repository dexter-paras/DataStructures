/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package org.atlassian.ratelimiter;

/**
 * @author paras.chawla
 * @version $Id: RateLimitter.java, v 0.1 2020-12-29 10:42 AM paras.chawla Exp $$
 * <p>
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 * <p>
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author paras.chawla
 * @version $Id: RateLimitter.java, v 0.1 2020-12-29 10:05 AM paras.chawla Exp $$
 */

// customerId - 10 Req per 1 min
// TokenBucket Algo
// Sliding Window
// 0,1 .....59,1 min, 1 min 1 sec .....
// 10 Req per customerId(123)
// 1 token <- multiple threads
public class RateLimitter {

    // userid->00.00.01-10,00.00.10-1
    private Map<Integer, TreeMap<Long, Integer>> rateLimitingMap;
    private int                                  numberOfReq = 0;
    private long                                 timeToServe;

    public RateLimitter(int numberOfReq, int timeToServe) {
        this.numberOfReq = numberOfReq;
        this.rateLimitingMap = new HashMap<Integer, TreeMap<Long, Integer>>();
        this.timeToServe = timeToServe;
    }

    // userid->00.00.01-1,00.00.10-1,00.00.59 -7 , 00.00.02.01
    boolean rateLimit(int customerId) {

        Long now = System.currentTimeMillis();
        Map<Long, Integer> existingRateLimitingData=null;
        if (rateLimitingMap.containsKey(customerId)) {
            existingRateLimitingData = rateLimitingMap.get(customerId);

            int count = 0;
            for (Map.Entry<Long, Integer> entry : existingRateLimitingData.entrySet()) {
                if (now - entry.getKey()> timeToServe) {
                   break;
                }
                count += entry.getValue();
            }

            if (count < numberOfReq) {
                return true;
            }
            return false;
        }

        if(existingRateLimitingData.containsKey(now)){
            int count= existingRateLimitingData.get(now);
            existingRateLimitingData.put(now,++count);
            rateLimitingMap.put(customerId,existingRateLimitingData);
        }
        return false;
    }

}