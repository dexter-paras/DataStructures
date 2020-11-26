/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.test;

/**
 * @author paras.chawla
 * @version $Id: EnvInfo.java, v 0.1 2020-03-23 19:28 paras.chawla Exp $$
 */
public class EnvInfo {

    String sessionId;
    String tokenId;
    String orderTerminalId;

    public EnvInfo() {
    }

    public EnvInfo(String sessionId) {
        this.sessionId = sessionId;
    }

    public EnvInfo(String sessionId, String tokenId, String orderTerminalId) {
        this.sessionId = sessionId;
        this.tokenId = tokenId;
        this.orderTerminalId = orderTerminalId;
    }
}