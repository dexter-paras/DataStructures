/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.contest.salesforce;

/**
 * @author paras.chawla
 * @version $Id: Test.java, v 0.1 2021-02-06 11:45 AM paras.chawla Exp $$
 */
public class Test {

    public static int firstOccurrence(String s, String x) {

        for(int i=0; i< s.length();i++){ //s="juliasamanthantjulia"
            // Find indexes from s which matches first character of x
            if(s.charAt(i)== x.charAt(0) || x.charAt(0)=='*'){ //x="ant"
                int k =i;
                int firstOccurence =k;
                int j;
                for(j=0; j< x.length();j++){ // till length of x only
                    if(s.charAt(k)==x.charAt(j) || x.charAt(j)=='*'){
                        k++;
                    }else{break;}
                }

                if(j==x.length()){
                    return firstOccurence;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //firstOccurrence("juliasamanthantjulia","ant");
        System.out.println("juliasamanthantjulia".indexOf("ant"));
        System.out.println("xadeypqr".indexOf("adeyp"));
    }
}