/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.designpattern.singleton;

/**
 * @author paras.chawla
 * @version $Id: RegistryEnum.java, v 0.1 2020-10-02 01:18 paras.chawla Exp $$
 */

// Enums can't be extended , hence no inheritance problem
// Normal classes if deserialized can generate another instance , enums take care of this problem by itself
public enum RegistryEnum {

    INSTANCE;

    public RegistryEnum getInstance() {
        return INSTANCE;
    }
}