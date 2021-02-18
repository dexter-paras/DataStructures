/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.uc;

import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: DataProvider.java, v 0.1 2020-12-07 18:38 paras.chawla Exp $$
 */
public interface DataProvider {

    void add(int jobId,int startValue);

    void delete(int jobId);

    List<Pair> get(int startValue);

}