/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.companies.amazonoa;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: PartitionString.java, v 0.1 2020-05-25 17:23 paras.chawla Exp $$
 */
public class PartitionString {

    public List<Integer> partitionLabels(String S) {
        if (S == null || S.length() == 0) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        int lastIndexAdded = 0;
        int probablePartitionIndex = 0;
        int count = 0;
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i); // d
            for (int j = i + 1; j < S.length(); j++) {
                if (ch == S.charAt(j)) {
                    probablePartitionIndex = Math.max(probablePartitionIndex, j);//8
                }
            }
            if (i == probablePartitionIndex) {
                if (lastIndexAdded == 0 && count == 0) {
                    list.add(i - lastIndexAdded + 1);
                    count++;
                } else {
                    list.add(i - lastIndexAdded);
                }
                lastIndexAdded = i;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new PartitionString().partitionLabels("caedbdedda"));//"eaaaabaaec"
        //ababcbacadefegdehijhklij
    }
}