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
 * Holding Price Details attribute mapped with http url
 */
public class HoldingPriceDetails {

    int totalRecords;
    List<HoldingPrice> data;

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<HoldingPrice> getData() {
        return data;
    }

    public void setData(List<HoldingPrice> data) {
        this.data = data;
    }
}