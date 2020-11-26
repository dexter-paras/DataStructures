/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: FizzBuzz.java, v 0.1 2020-03-01 20:15 paras.chawla Exp $$
 */
public class FizzBuzz {

    public List<String> fizzBuzz(int n) {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String output = "";
            if (i % 3 == 0) { output += "Fizz"; }
            if (i % 5 == 0) { output += "Buzz"; }
            if (output.isEmpty()) {output += i;}
            list.add(output);
        }
        return list;
    }
}