/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.companies.arcesium.footballSelection;

/**
 * @author paras.chawla
 * @version $Id: SelectionResult.java, v 0.1 2020-05-10 13:59 paras.chawla Exp $$
 */
public class SelectionResult {

    String name;

    SelectionStatus selectionStatus;

    PlayerType playerType;

    public SelectionResult(String name, SelectionStatus selectionStatus, PlayerType playerType) {
        this.name = name;
        this.selectionStatus = selectionStatus;
        this.playerType = playerType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSelectionStatus(SelectionStatus selectionStatus) {
        this.selectionStatus = selectionStatus;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}