/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.companies.arcesium.stockHolding;

/**
 * @author paras.chawla
 * @version $Id: BaseHolding.java, v 0.1 2019-09-15 18:05 paras.chawla Exp $$
 */

/**
 * Base class holding common attributes
 */
public class BaseHolding {
    String date;
    String security;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

}