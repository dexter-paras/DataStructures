/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.module.genericProgramming.impl;

import com.module.genericProgramming.models.AcquireBaseRequest;
import com.module.genericProgramming.models.AqcBaseResult;

/**
 * @author paras.chawla
 * @version $Id: AcquirecenterBizCallback.java, v 0.1 2020-05-12 10:28 paras.chawla Exp $$
 */

// Center Biz CallBack class with datatype as Req extends AcquireBaseRequest, Res extends AqcBaseResult
// deals with obj which extends AcquireBaseRequest only
// deals with obj which extends AqcBaseResult only
public class AcquirecenterBizCallback<Req extends AcquireBaseRequest, Res extends AqcBaseResult>
        extends AcquireBizBaseCallback<Req, Res> {
}