/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.nobroker;

import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: OfficeDesign.java, v 0.1 2020-09-13 20:09 paras.chawla Exp $$
 */
public class OfficeDesign {

    public static int getMaxColors(List<Integer> prices, int money) {

        if (prices.size() == 0 || money == 0) {
            return 0;
        }

        int maxCount = 0;

        for (int i = 0; i < prices.size(); i++) {
            int count = 0;
            int sum = 0;
            for (int j = i; j < prices.size(); j++) {
                sum += prices.get(j);
                count++;
                if (sum <= money) {
                    maxCount = Math.max(maxCount, count);
                }
            }
        }
        return maxCount;
    }

    /*public static int getMaxColorsDp(List<Integer> prices, int money) {

    }*/

}