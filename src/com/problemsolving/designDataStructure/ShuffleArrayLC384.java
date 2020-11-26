/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.designDataStructure;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author paras.chawla
 * @version $Id: ShuffleArrayLC384.java, v 0.1 2019-08-23 14:31 paras.chawla Exp $$
 */
public class ShuffleArrayLC384 {

    int[] array;
    int[] original;

    public ShuffleArrayLC384(int[] array) {
        this.array = array;
        this.original = array.clone();
    }

    public static void main(String[] args) {
        int[] array = {5, 7, 2, 9};
        ShuffleArrayLC384 obj = new ShuffleArrayLC384(array);
        int count = 1;
        while (count++ < 100) {
            System.out.println("Case :" + count);
            obj.shuffleArray(array);
            obj.reset();
        }
    }

    private int[] reset() {
        array = original;
        original = original.clone();
        System.out.println("Original array" + Arrays.toString(original));
        System.out.println("Reseting shuffled array to Original" + Arrays.toString(array));
        return original;
    }

    private int[] shuffle(int array[]) {
        int length = array.length;//4
        while (length > 1) {
            int randomIndex = (int) Math.random() * length;
            swap(randomIndex, length - 1);
            length--;
        }
        return array;
    }

    //a=7,b=6
    private void swap(int randomIndex, int lastIndex) {
        int temp = array[randomIndex];//7
        array[randomIndex] = array[lastIndex];//6
        array[lastIndex] = temp;//7
    }

    static void shuffleArray(int[] array) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = array.length - 1; i > 0; i--) {
            int rndIndex = rnd.nextInt(i + 1);
            // Simple swap
            int temp = array[rndIndex];
            array[rndIndex] = array[i];
            array[i] = temp;
        }
        System.out.println("Shuffled array" + Arrays.toString(array));
    }
}