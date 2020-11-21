/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.oracle.validator;

import com.oracle.exception.IPOctetOutOfBoundException;

import java.text.ParseException;

/**
 * @author paras.chawla
 * @version $Id: ValidatorUtil.java, v 0.1 2020-11-04 09:44 paras.chawla Exp $$
 */
public class ValidatorUtil {

    // 287.12.412.32
    // hello..1.world empty string

    public static boolean validate(String str) {

        StringBuilder description = new StringBuilder();

        String[] tokens = str.split("\\.");

        if (tokens.length != 4) {
            // assert exception , curernt token length and put it in description

        } else {
            for (int i = 0; i < tokens.length; i++) {
                int octet = 0;
                // 1. convert String to integer and if not valid integer,note that as well
                try {
                    octet = Integer.parseInt(tokens[i]);
                } catch (NumberFormatException e) {
                    description.append(tokens[i] + "should be a valid Ip only in range [0-255]");
                    continue;
                }

                // 2. validate octet
                if (octet < 0 || octet > 255) {
                    description.append("octet" + i + " should be in range [0-255]");
                }
            }

            if (description.length() > 0) {
                System.out.println(description.length());
                throw new IPOctetOutOfBoundException(description.toString());
            }
        }
        return true;
    }

}