/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.multithreading.part3_daemonandinterrupt;

import java.math.BigInteger;

/**
 * @author paras.chawla
 * @version $Id: ComplexCalculation.java, v 0.1 2020-08-25 00:20 paras.chawla Exp $$
 */

/*
There are times when we want 1 thread to complete before another thread starts
More control over worker thread
*/
public class ComplexCalculation {
    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2)
            throws InterruptedException {
        BigInteger result;

        // 1. create thread1 to calculate base1 ^ power1
        PowerCalculatingThread thread1 = new PowerCalculatingThread(base1, power1);

        // 2. create thread2 to calculate base1 ^ power1
        PowerCalculatingThread thread2 = new PowerCalculatingThread(base2, power2);

        thread1.start();
        thread2.start();

        // 3. Want thread1 and thread2 to complete and then only main() proceeds
        thread1.join();
        thread2.join();

        // 4. aggregating result
        result = thread1.getResult().add(thread2.getResult());

        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            for (BigInteger i = BigInteger.ZERO;
                 i.compareTo(power) != 0;
                 i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }
        }

        public BigInteger getResult() { return result; }
    }
}