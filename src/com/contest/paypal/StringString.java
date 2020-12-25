/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.paypal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: StringString.java, v 0.1 2020-12-08 10:27 paras.chawla Exp $$
 */
public class StringString {

    static int totalWeight = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine().trim());
        for (int t_i = 0; t_i < T; t_i++) {
            String str = br.readLine();
            int N = Integer.parseInt(str);

            String[] strings = new String[N];
            for (int i = 0; i < N; i++) {
                strings[i] = br.readLine();
            }

            System.out.println(invalidString(strings));
        }

        wr.close();
        br.close();
    }

    private static String invalidString(String[] strings) {

        int minOperations = Integer.MAX_VALUE;
        HashMap<String, Integer> weightMap = createWeightMap(strings);

        String invalidString = "";

        for (Map.Entry<String, Integer> entry : weightMap.entrySet()) {

            // exclude first entry and find avg of all others // Chakshu-71
            int average = (int) Math.ceil((double)(totalWeight - entry.getValue()) / (weightMap.size() - 1));
            int operations = 0;
            for (Map.Entry<String, Integer> e : weightMap.entrySet()) {

                if (!e.getKey().equals(entry.getKey())) {
                    operations += Math.abs(average - e.getValue());
                }
            }

            if (operations < minOperations) {
                invalidString=entry.getKey();
                minOperations = Math.min(operations, minOperations);
            }
        }

        return invalidString;
    }

    private static HashMap<String, Integer> createWeightMap(String[] strings) {

        HashMap<String, Integer> weightMap = new HashMap<>();

        for (String str : strings) {
            int sum = 0;
            for (char c : str.toCharArray()) {
                sum += c - 'a' + 1;
            }
            weightMap.put(str, sum);
            totalWeight += sum;
        }

        return weightMap;
    }
}