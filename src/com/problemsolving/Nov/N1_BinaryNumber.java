/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.Nov;

import com.problemsolving.linkedList.model.ListNode;

/**
 * @author paras.chawla
 * @version $Id: N1_BinaryNumber.java, v 0.1 2020-11-02 10:53 paras.chawla Exp $$
 */
public class N1_BinaryNumber {

    public int getDecimalValue(ListNode head) {
        if (head == null) {
            return -1;
        }

        StringBuilder builder = new StringBuilder();
        while (head != null) {
            builder.append(head.val);
        }

        int binaryNumber = Integer.parseInt(builder.toString());
        return 1;
    }
}