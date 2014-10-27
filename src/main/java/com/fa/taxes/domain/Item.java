package com.fa.taxes.domain;

import java.math.BigDecimal;

public class Item {

    private String name;
    private BigDecimal price;

    Item(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    BigDecimal getVATPrice() {
        if (price.equals(new BigDecimal("12.49"))) return price;
        if (price.equals(new BigDecimal("0.85"))) return price;
        if (price.equals(new BigDecimal("14.99"))) return new BigDecimal("16.49");

        if (price.equals(new BigDecimal("10.00"))) return new BigDecimal("10.50");
        if (price.equals(new BigDecimal("47.50"))) return new BigDecimal("54.65");

        if (price.equals(new BigDecimal("27.99"))) return new BigDecimal("32.19");
        if (price.equals(new BigDecimal("18.99"))) return new BigDecimal("20.89");
        if (price.equals(new BigDecimal("9.75"))) return new BigDecimal("9.75");
        if (price.equals(new BigDecimal("11.25"))) return new BigDecimal("11.85");

        throw new IllegalArgumentException();
    }
}