/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.oracle.exception;

/**
 * @author paras.chawla
 * @version $Id: IPOctetOutOfBoundException.java, v 0.1 2020-11-04 09:45 paras.chawla Exp $$
 */
public class IPOctetOutOfBoundException extends RuntimeException {

    String errorDescription="";

    public IPOctetOutOfBoundException(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public IPOctetOutOfBoundException(String message, String errorDescription) {
        super(message);
        this.errorDescription = errorDescription;
    }
}