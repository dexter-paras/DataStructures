/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.designpattern.template.impl;

import com.designpattern.template.models.AcquireBaseRequest;
import com.designpattern.template.models.AqcBaseResult;

/**
 * @author paras.chawla
 * @version $Id: AcquirecenterBizCallback.java, v 0.1 2020-05-12 10:28 paras.chawla Exp $$
 */

// Center Biz CallBack class with datatype as Req extends AcquireBaseRequest, Res extends AqcBaseResult
// deals with obj which extends AcquireBaseRequest only
// deals with obj which extends AqcBaseResult only
public abstract class AcquirecenterBizCallback<Req extends AcquireBaseRequest, Res extends AqcBaseResult>
        extends AcquireBizBaseCallback<Req, Res> {


    @Override
    public void preProcess(Req request, Res result) {
        bizPreProcess(request, result);
    }

    @Override
    public void process(Req request, Res result) {
        bizProcess(request, result);
    }


}