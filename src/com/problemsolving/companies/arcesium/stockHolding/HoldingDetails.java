/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.companies.arcesium.stockHolding;

import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: HoldingDetails.java, v 0.1 2019-09-15 17:24 paras.chawla Exp $$
 */

/**
 * Holding Details attribute mapped with http url
 */
public class HoldingDetails {

    int totalRecords;
    List<Holding> data;

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<Holding> getData() {
        return data;
    }

    public void setData(List<Holding> data) {
        this.data = data;
    }
}