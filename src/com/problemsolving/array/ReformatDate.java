/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: ReformatDate.java, v 0.1 2020-12-09 22:10 paras.chawla Exp $$
 */
public class ReformatDate {

    public static String reformatDate(String date) {

        Map<String, String> monthsMap = new HashMap<>();
        monthsMap.put("Jan", "01");
        monthsMap.put("Feb", "02");
        monthsMap.put("Mar", "03");
        monthsMap.put("Apr", "04");
        monthsMap.put("May", "05");
        monthsMap.put("Jun", "06");
        monthsMap.put("Jul", "07");
        monthsMap.put("Aug", "08");
        monthsMap.put("Sep", "09");
        monthsMap.put("Oct", "10");
        monthsMap.put("Nov", "11");
        monthsMap.put("Dec", "12");

        String[] tokens = date.split(" ");

        // date= 20th Jun 2020

        // 2020
        String year = tokens[2];

        // Jun -06
        String month = monthsMap.get(tokens[1]);

        // "6th" -06 , "20th" - 20
        String dat = tokens[0].length() == 3 ? "0" + tokens[0].charAt(0) : tokens[0].substring(0, 2);

        return year + "-" + month + "-" + dat;
    }

    public static void main(String[] args) {

        System.out.println(reformatDate("20th Oct 2052"));
        System.out.println(reformatDate("6th Jun 1933"));
        System.out.println(reformatDate("26th May 1960"));
    }
}