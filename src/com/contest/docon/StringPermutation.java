/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.docon;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: StringPermutation.java, v 0.1 2020-11-11 18:20 paras.chawla Exp $$
 */
public class StringPermutation {

    List<String> result = new ArrayList<>();

    private List<String> stringPermutation(String str) {

        if (str == null || str.length() == 0) { return result; }

        dfs("", str);
        return result;
    }

/*                    prefix     suffix
                       ""         man
                                  ^
                                  "" +an
dfs("",man)    i=0     m.         an              i=1                                i=2
dfs(m,an)      i=0     ma          n              i=1        mn     a
dfs(man,"")    i=0     man         ""             i=0        mna    ""
*/

    void dfs(String prefix, String suffix) {

        // base condition
        if (suffix.length() == 0) {
            result.add(prefix);
        }

        for (int i = 0; i < suffix.length(); i++) {
            dfs(prefix + suffix.charAt(i), suffix.substring(0, i) + suffix.substring(i + 1));
        }
    }

    public static void main(String[] args) {
        StringPermutation obj = new StringPermutation();
        obj.stringPermutation("ma");
        System.out.println(obj.result);
    }
}