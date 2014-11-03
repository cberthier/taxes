package com.fa.taxes.domain;

import org.apache.tapestry5.func.F;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.fa.taxes.domain.Product.toTotalPriceWithVAT;
import static com.fa.taxes.domain.Product.toTotalVAT;
import static java.math.BigDecimal.ZERO;

public class Invoice {

    List<Product> products = new ArrayList<>();

    public Invoice add(Product product) {
        products.add(product);
        return this;
    }

    public BigDecimal getTotalVAT() {
        return F.flow(products).reduce(toTotalVAT, ZERO);
    }

    public BigDecimal getTotalAmountWithVAT() {
        return F.flow(products).reduce(toTotalPriceWithVAT, ZERO);
    }

}