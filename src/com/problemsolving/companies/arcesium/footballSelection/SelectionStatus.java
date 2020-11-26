/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.companies.arcesium.footballSelection;

/**
 * @author paras.chawla
 * @version $Id: SelectionStatus.java, v 0.1 2020-05-10 14:00 paras.chawla Exp $$
 */
public enum SelectionStatus {

    SELECT("SELECT"),

    REJECT("REJECT"),
    ;

    private final String status;

    SelectionStatus(String status) {
        this.status = status;
    }

    public String value() {
        return status;
    }

    public static SelectionStatus getByValue(String value) {
        for (SelectionStatus as : values()) {
            if (as.value().equals(value)) {
                return as;
            }
        }
        throw new RuntimeException("cann't find");
    }

}