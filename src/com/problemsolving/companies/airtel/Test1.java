/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.companies.airtel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: Test1.java, v 0.1 2020-05-13 16:11 paras.chawla Exp $$
 */
public class Test1 {

    public static void main(String args[]) throws Exception {

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();                // Reading input from STDIN

        HashMap<Website, Integer> map = new HashMap<>();

        // Write your code here
        int count = 0;
        while (count < Integer.parseInt(input)) {
            String website = br.readLine();
            Website obj = new Website(website);
            if (!map.containsKey(obj)) {
                map.put(obj, 1);
            } else {
                int value = map.get(obj);
                map.put(obj, ++value);
            }
        }
    }
}

class Website {

    String url;

    public Website(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    List<Website> getMostVisitedPages() {
        return null;
    }
}