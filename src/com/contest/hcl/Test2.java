/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.hcl;

/**
 * @author paras.chawla
 * @version $Id: Test.java, v 0.1 2020-08-29 14:00 paras.chawla Exp $$
 */

interface Syrupable {
    void getSugary();
}

abstract class PanCake implements Syrupable {}

class BlueBerryPanCake extends PanCake {
    public void getSugary() {;}
}

class SourdoughBlueBerryPanCake extends BlueBerryPanCake {
    public void getSugary() {;}
}