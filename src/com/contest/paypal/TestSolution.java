/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.paypal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author paras.chawla
 * @version $Id: TestSolution.java, v 0.1 2020-12-08 10:07 paras.chawla Exp $$
 */
public class TestSolution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine().trim());
        for (int t_i = 0; t_i < T; t_i++) {
            String[] str = br.readLine().split(" ");
            int L = Integer.parseInt(str[0]);
            int R = Integer.parseInt(str[1]);

            int out_ = solution(L, R);
            System.out.println(out_);
        }

        wr.close();
        br.close();
    }

    static int solution(int L, int R) {
        int result = 0;
        for (int i = L; i <= R; i++) {
            result = gcd(result, i);

            if (result == 1) {
                return 1;
            }
        }
        return result;
    }

    static int gcd(int a, int b) {
        if (a == 0) { return b; }
        return gcd(b % a, a);
    }
}
