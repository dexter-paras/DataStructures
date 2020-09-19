/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.module.genericProgramming.impl;

import com.module.genericProgramming.models.AcquireBaseRequest;
import com.module.genericProgramming.models.AcquireBaseResult;

/**
 * @author paras.chawla
 * @version $Id: AcquireBizBaseCallback.java, v 0.1 2020-05-12 10:29 paras.chawla Exp $$
 */

// Base Callback Class with datatype as Req and Res
// deals with Req obj which extend with AcquireBaseRequest only
// deals wtih Res obj which extend with AcquireBaseResult only
public abstract class AcquireBizBaseCallback<Req extends AcquireBaseRequest, Res extends AcquireBaseResult> {

    protected void preProcess(Req req, Res res) {
        bizPreProcess(req, res);
    }

    protected void bizPreProcess(Req req, Res res) {

    }

}