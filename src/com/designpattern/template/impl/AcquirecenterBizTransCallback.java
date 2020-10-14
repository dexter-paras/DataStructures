/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.designpattern.template.impl;

import com.designpattern.template.models.AqcBaseResult;
import com.designpattern.template.models.TransRequest;

/**
 * @author paras.chawla
 * @version $Id: AcquirecenterBizTransCallback.java, v 0.1 2020-05-12 10:28 paras.chawla Exp $$
 */
public abstract class AcquirecenterBizTransCallback<Req extends TransRequest, Res extends AqcBaseResult>
        extends AcquirecenterBizCallback<Req, Res> {
}