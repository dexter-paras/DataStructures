/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.module.designpattern.template.impl;

import com.module.designpattern.template.models.AcquireBaseRequest;
import com.module.designpattern.template.models.AcquireBaseResult;

import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: AcquireBizBaseCallback.java, v 0.1 2020-05-12 10:29 paras.chawla Exp $$
 */

// Base Callback Class with datatype as Req and Res
// deals with Req obj which extend with AcquireBaseRequest only
// deals wtih Res obj which extend with AcquireBaseResult only
public abstract class AcquireBizBaseCallback<Req extends AcquireBaseRequest, Res extends AcquireBaseResult> {

    public void preProcess(Req req, Res res) {
        bizPreProcess(req, res);
    }

    public void bizPreProcess(Req req, Res res) {

    }

    public void process(Req request, Res result) {
        bizProcess(request, result);
    }

    public abstract Res createDefaultResult();

    public abstract void bizProcess(Req request, Res result);

}