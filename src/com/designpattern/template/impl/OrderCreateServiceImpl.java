/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.designpattern.template.impl;

import com.designpattern.template.models.OrderCreateRequest;
import com.designpattern.template.models.OrderCreateResult;
import com.designpattern.template.temp.AcquireServiceTemplate;

/**
 * @author paras.chawla
 * @version $Id: OrderCreateServiceImpl.java, v 0.1 2020-05-12 10:28 paras.chawla Exp $$
 */

public class OrderCreateServiceImpl extends AcquirecenterBizTransCallback<OrderCreateRequest, OrderCreateResult> {

    AcquireServiceTemplate template = new AcquireServiceTemplate();

    public OrderCreateResult create(final OrderCreateRequest orderCreateRequest) {
        return template.execute(orderCreateRequest, this);
    }

    @Override
    public OrderCreateResult createDefaultResult() {
        return new OrderCreateResult();
    }

    @Override
    public void bizPreProcess(OrderCreateRequest request, OrderCreateResult result) {
        System.out.println("Implementation class- OrderCreate - Pre Process Logic");
    }

    @Override
    public void bizProcess(OrderCreateRequest request, OrderCreateResult result) {
        System.out.println("Implementation class- OrderCreate - Process Logic");
    }

}