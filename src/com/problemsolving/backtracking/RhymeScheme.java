/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.backtracking;

/**
 * @author paras.chawla
 * @version $Id: RhymeScheme.java, v 0.1 2020-10-11 15:01 paras.chawla Exp $$
 */
import java.util.ArrayList;
import java.util.List;

public class RhymeScheme {
    public List<String[]> dfs(int N) {
        List<String[]> result = new ArrayList<>();
        if (N == 1) {
            result.add(new String[] {"A", 1 + ""});
            return result;
        }

        List<String[]> list = dfs(N - 1);
        for (String pair[] : list) {
            String path = pair[0];
            int count = Integer.parseInt(pair[1]);
            for (int i = 0; i <= count; i++) {
                int nextCount = count;
                if (i == count) nextCount++;
                result.add(new String[] {path + (char) ('A' + i), nextCount + ""});
            }
        }
        return result;
    }

    public static void main(String[] args) {
        RhymeScheme rhythm = new RhymeScheme();
        List<String[]> result = rhythm.dfs(3);
        System.out.println(result.size());
        for (String pair[] : result) System.out.println(pair[0]);
    }
}