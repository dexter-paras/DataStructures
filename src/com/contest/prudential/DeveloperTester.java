/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.prudential;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: DeveloperTester.java, v 0.1 2020-09-16 23:29 paras.chawla Exp $$
 */
public class DeveloperTester {

    public static List<Integer> formTeam(int d, int t, List<Integer> q) {

        List<Integer> result = new ArrayList<>();

        // base condition
        if (q.size() == 0) {
            return result;
        }

        for (int i = 0; i < q.size(); i++) {
            int possibleWays = possibleWaysFromCombination((int) q.get(i), d, t);
            result.add(possibleWays);
        }
        return result;
    }

    static int possibleWaysFromCombination(int teamSize, int dev, int qa) {
        return 0;
    }

}