/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.genericProgramming;

/**
 * @author paras.chawla
 * @version $Id: Box.java, v 0.1 2019-09-27 18:12 paras.chawla Exp $$
 */

/* Box class with type T*/
public class Box<T> {

    T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    /* Concept 4 : Bounded Generics --> we can restrict types that can be accepted by a method
     * Box of Type T can accept only Integer and its subType in this case*/
    public <T extends Number> void inspect(T t) {
        System.out.println(t.getClass().getName());
    }

}