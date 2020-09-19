/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.amazonoa1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: ExtraCandies.java, v 0.1 2020-08-23 12:14 paras.chawla Exp $$
 */
public class ExtraCandies {

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {

        List<Boolean> result = new ArrayList<>(candies.length);
        if (candies == null || candies.length == 0) {
            return result;
        }

        // Find Max in candies
        int max = candies[0];
        for (int i = 1; i < candies.length; i++) {
            max = Math.max(max, candies[i]);
        }

        // traverse and check
        for (int i = 0; i < candies.length; i++) {
            Boolean bol = candies[i] + extraCandies >= max ? Boolean.TRUE : Boolean.FALSE;
            result.add(bol);
        }
        return result;
    }
}