package com.fa.taxes.domain;

import java.math.BigDecimal;
import java.util.Objects;

import static java.math.BigDecimal.ZERO;
import static java.util.Objects.requireNonNull;


public class Product {

    private String name;
    private ProductType productType;
    private Origin origin;
    private BigDecimal price;

    Product(String name, ProductType productType, Origin origin, BigDecimal price) {
        this.name = requireNonNull(name);
        this.productType = requireNonNull(productType);
        this.origin = requireNonNull(origin);
        this.price = requireNonNull(price);
        assert price.compareTo(ZERO) >= 0;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPriceWithVAT() {
        return price.add(getVAT());
    }

    public BigDecimal getVAT() {
        return new Taxe(price, productType.getVATRate(), origin.getVATRate()).getVAT();
    }

    @Override
    public String toString() {
        return "1 " + getName() + " : " + getPriceWithVAT();
    }
}
