/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.designDataStructure;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author paras.chawla
 * @version $Id: KeyHashCodeAndEquals.java, v 0.1 2020-02-13 15:40 paras.chawla Exp $$
 */
public class KeyHashCodeAndEquals {

    public static void main(String[] args) {
        //Product product = new Product("Paras", "I'm Awesome");
        //Product product1 = new Product("Paras", "I'm Awesome");

        HashMap<String, Integer> map = new HashMap<>();
        map.put("Paras", 1);
        map.put("Paras", 2);
        System.out.println(map.size());
        System.out.println(map.get("Paras"));
    }
}

class Product {
    String name;
    String description;

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}