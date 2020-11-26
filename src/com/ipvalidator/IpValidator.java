/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.ipvalidator;

import com.ipvalidator.validator.ValidatorUtil;

/**
 * @author paras.chawla
 * @version $Id: IpValidator.java, v 0.1 2020-11-04 09:35 paras.chawla Exp $$
 */
public class IpValidator {

    String str = "hello java"; // "10.120.123.12" ,"100.1.23.222" , "356.1.0.259"

    // template DP -> preprocess -> process -> postprocess

    public static void main(String[] args) {
        IpValidator obj = new IpValidator();
        //System.out.println(obj.validIpAddress("123.12.41.141"));
        obj.validIpAddress("hello.world.287.141");

    }

    boolean validIpAddress(String str) {
        return ValidatorUtil.validate(str);
    }

}