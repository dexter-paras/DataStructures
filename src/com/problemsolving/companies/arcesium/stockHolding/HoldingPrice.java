/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.companies.arcesium.stockHolding;

/**
 * @author paras.chawla
 * @version $Id: Holding.java, v 0.1 2019-09-15 17:23 paras.chawla Exp $$
 */

/**
 * Holding price attributes
 */
public class HoldingPrice extends BaseHolding {

    double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}