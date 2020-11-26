/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.companies.airtel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: Test1.java, v 0.1 2020-05-13 16:11 paras.chawla Exp $$
 */
public class Test2 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();                // Reading input from STDIN

        List<Pair> list = new ArrayList<>();

        // Write your code here
        int count = 0;
        while (count < Integer.parseInt(input)) {

            String[] tokens = br.readLine().split(" ");
            list.add(new Pair(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])));
            count++;
        }

        list.sort(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.getX() != o2.getX()) {
                    return o1.getX() - o2.getX();
                } else {
                    return o2.getY() - o1.getY();
                }
            }
        });

        for (Pair pair : list) {
            System.out.println(pair.getX() + " " + pair.getY());
        }
    }
}

class Pair {

    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
