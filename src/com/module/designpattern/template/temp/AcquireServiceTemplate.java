/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.module.designpattern.template.temp;

import com.module.designpattern.template.impl.AcquireBizBaseCallback;
import com.module.designpattern.template.models.AcquireBaseRequest;
import com.module.designpattern.template.models.AcquireBaseResult;

/**
 * @author paras.chawla
 * @version $Id: AcquireServiceTemplate.java, v 0.1 2020-09-28 17:59 paras.chawla Exp $$
 */
public class AcquireServiceTemplate {

    public <T extends AcquireBaseRequest, R extends AcquireBaseResult> R
    execute(T request, AcquireBizBaseCallback<T, R> action) {
        return execute(request, action, true);
    }

    private <R extends AcquireBaseResult, T extends AcquireBaseRequest> R execute(T request, AcquireBizBaseCallback<T, R> action,
                                                                                  boolean onTxn) {

        R result = action.createDefaultResult();

        action.preProcess(request, result);

        action.process(request, result);

        return result;
    }
}