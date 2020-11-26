/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.Misc;

import java.util.Arrays;

/**
 * @author paras.chawla
 * @version $Id: DistributeCandies.java, v 0.1 2020-08-17 21:10 paras.chawla Exp $$
 */
public class DistributeCandies {

    public int[] distributeCandies(int candies, int num_people) {
        int[] array = new int[num_people];

        int index = 0;
        int currCount = 1;

        while (candies > 0) {
            if (currCount <= candies) {
                array[index] = array[index] + currCount++;
            } else {
                array[index] +=candies;
                break;
            }
            candies = candies - array[index++];
            if (index == num_people) {
                index = 0;
            }
        }
        return array;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new DistributeCandies().distributeCandies(7,3)));
        System.out.println(Arrays.toString(new DistributeCandies().distributeCandies(10,3)));
        System.out.println(Arrays.toString(new DistributeCandies().distributeCandies(15,3)));
        System.out.println(Arrays.toString(new DistributeCandies().distributeCandies(20,3)));
        System.out.println(Arrays.toString(new DistributeCandies().distributeCandies(25,3)));
        System.out.println(Arrays.toString(new DistributeCandies().distributeCandies(30,3)));
    }
}