/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.companies.arcesium.footballSelection;

/**
 * @author paras.chawla
 * @version $Id: SelectionStatus.java, v 0.1 2020-05-10 14:00 paras.chawla Exp $$
 */
public enum PlayerType {

    STRIKER("STRIKER"),

    DEFENDER("DEFENDER"),

    NA("NA"),
    ;

    private final String playerType;

    PlayerType(String playerType) {
        this.playerType = playerType;
    }

    public String value() {
        return playerType;
    }

    public static PlayerType getByValue(String playerType) {
        for (PlayerType as : values()) {
            if (as.value().equals(playerType)) {
                return as;
            }
        }
        throw new RuntimeException("cann't find");
    }

}