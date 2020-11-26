/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.ally;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author paras.chawla
 * @version $Id: FindUniquePairs.java, v 0.1 2020-06-04 09:30 paras.chawla Exp $$
 */
public class FindUniquePairs {

    int findUniquePairs(int[] array) {

        // 1,2,3,4,5
        // ^   ^

        int max1 = array[0], max2 = 0;
        Pair pair1 = null;
        Set<Pair> set = new HashSet<>();
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] > max1) {
                    max2 = max1;//2
                    max1 = array[j];//1
                } else if (array[j] < max1 && array[j] > max2) {
                    max2 = array[j];
                }
                Pair pair = new Pair(max1, max2);
                set.add(pair);

                if (max1 != array[j]) {
                    pair1 = new Pair(max1, array[j]);
                    set.add(pair1);
                }
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        System.out.println(new FindUniquePairs().findUniquePairs(new int[] {4, 3, 2, 1, 6, 5, 7}));
    }
}

class Pair {

    int number1;
    int number2;

    public Pair(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Pair pair = (Pair) o;
        return number1 == pair.number1 &&
                number2 == pair.number2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number1, number2);
    }
}