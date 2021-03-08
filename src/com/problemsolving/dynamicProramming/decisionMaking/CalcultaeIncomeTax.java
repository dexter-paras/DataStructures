/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.dynamicProramming.decisionMaking;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: CalcultaeIncomeTax.java, v 0.1 2021-03-07 11:23 AM paras.chawla Exp $$
 */
public class CalcultaeIncomeTax {

    public static void main(String[] args) {

        //[ [10000, 0.3],[20000, 0.2], [30000, 0.1], [null, .1]]

        double[] max = {0, 10000, 30000, 60000, 99999999};
        double[] rate = {0, 0.30, .20, 0.1, 0.1};

        Map<Double, Double> itSlabMap = new LinkedHashMap<>();
        itSlabMap.put(10000d, .3d);
        itSlabMap.put(20000d, .2d);
        itSlabMap.put(30000d, .1d);
        itSlabMap.put(9999999d, .1d);

        System.out.println(calculateTax(65000, max, rate));
        System.out.println(calculateTax2(itSlabMap, 65000));

        System.out.println(calculateTax(35000, max, rate));
        System.out.println(calculateTax2(itSlabMap, 35000));

        System.out.println(calculateTax(25000, max, rate));
        System.out.println(calculateTax2(itSlabMap, 25000));

        System.out.println(calculateTax(15000, max, rate));
        System.out.println(calculateTax2(itSlabMap, 15000));

        System.out.println(calculateTax(5000, max, rate));
        System.out.println(calculateTax2(itSlabMap, 5000));
    }

    static double calculateTax(double income, double[] max, double[] rate) {
        double left = income;
        double tax = 0.0d;

        // no tax
        if (income < max[1]) { return 0; }

        for (int i = 1; i < max.length && left > 0; i++) {
            double df = Math.min(max[i] - max[i - 1], left);
            tax += rate[i] * df;
            left -= df;
        }
        return tax;
    }

    static double calculateTax2(Map<Double, Double> itSlabMap, double income) {
        double tax = 0d;

        for (Map.Entry<Double, Double> entry : itSlabMap.entrySet()) {
            if (income >= entry.getKey()) {
                tax += entry.getValue() * entry.getKey();
                income -= entry.getKey();
            } else if (tax != 0) {
                tax += entry.getValue() * income;
                break;
            } else {
                return tax;
            }
        }
        return tax;
    }

}

// 10000 - 3000 left = 55000
// 20000 - 4000 left = 35000
// 30000 - 3000 left = 5000
// >X -     500 left =0