/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.multithreading.part3_daemonandinterrupt;

import java.math.BigDecimal;

/**
 * @author paras.chawla
 * @version $Id: PrematureInterrupt.java, v 0.1 2020-08-25 00:04 paras.chawla Exp $$
 *
 */
public class PrematureInterrupt {

    public static void main(String[] args) {

        // 1. creating Thread and passing base and pow
        Thread thread = new Thread(new LongComputationTask(new BigDecimal("200"), new BigDecimal("1000")));

        // 2. starting thread , if base and pow are large number, it keeps running
        thread.start();

        // 3. using interrupt will do nothing until it is explicitly handdled
        thread.interrupt();
    }

}

class LongComputationTask implements Runnable {

    private BigDecimal base;
    private BigDecimal pow;

    public LongComputationTask(BigDecimal base, BigDecimal pow) {
        this.base = base;
        this.pow = pow;
    }

    @Override
    public void run() {
        System.out.println(base + "^" + pow + "= " + calculate(base, pow));
    }

    private BigDecimal calculate(BigDecimal base, BigDecimal pow) {

        BigDecimal result = BigDecimal.ONE;
        for (BigDecimal i = BigDecimal.ZERO; i.compareTo(pow) != 0; i=i.add(BigDecimal.ONE)) {

            // interrupt signal will get heard and action can be taken now
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Prematurely interrupted Computation");
                return BigDecimal.ZERO;
            }
            result = result.multiply(base);
        }
        return result;
    }
}