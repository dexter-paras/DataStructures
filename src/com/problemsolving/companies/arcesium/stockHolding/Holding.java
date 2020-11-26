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
 * Holding attributes
 */
public class Holding extends BaseHolding{

    double quantity;
    String portfolio;

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }
}