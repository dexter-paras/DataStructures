/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.genericProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author paras.chawla
 * @version $Id: GenericMethod.java, v 0.1 2019-09-27 17:38 paras.chawla Exp $$ https://www.baeldung.com/java-generics
 * https://docs.oracle.com/javase/tutorial/java/generics/bounded.html
 */
public class GenericMethod {

    // To find min of a list of values which can be Integer, Number or any object
    private static <T> T min(List<T> values, Comparator<T> comparator) {
        if (values.isEmpty()) {
            throw new IllegalArgumentException("List is Empty,can't find minimum");
        }

        T lowestValue = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            if (comparator.compare(values.get(i), lowestValue) < 0) {
                lowestValue = values.get(i);
            }
        }
        return lowestValue;
    }

    /* <T> in the method signature implies that the method will be dealing with generic type T */
    /* Convert array of Type T to list of Type T*/
    public <T> List<T> fromArrayToList(T[] array) {
        return Arrays.stream(array).collect(Collectors.toList());
    }

    /* Convert array of Type T to list of Type G*/
    public static <T, G> List<G> fromArrayToList(T[] array, Function<T, G> mapperFunction) {
        return Arrays.stream(array)
                .map(mapperFunction)
                .collect(Collectors.toList());
    }

    /*compare(P1,P2) to compare multiple Pair objects of type K,V */
    public <K, V> boolean compare(Pair<K, V> pair1, Pair<K, V> pair2) {
        return pair1.getKey().equals(pair2.getKey()) && pair1.getValue().equals(pair2.getValue());
    }

    public static void main(String[] args) {

        // Concept 4 -- Creating box of Integer Type
        Box<Integer> integerBox = new Box();
        integerBox.setT(new Integer(1));

        // inspect() is bounded to Number and its subclasses only
        //integerBox.inspect("I'm String");  <-- String doesn't extend Number ,hence compiler gives error
        integerBox.inspect(new Integer(1));
        integerBox.inspect(new Long(1));

        SortedPair<Integer> sortedPair = new SortedPair("Paras", "Sonal");
        System.out.println(sortedPair);

        // Generic min()
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(min(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        }));
    }
}