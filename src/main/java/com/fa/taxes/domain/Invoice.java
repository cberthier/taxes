package com.fa.taxes.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;

public class Invoice {

    List<Product> products = new ArrayList<>();

    public Invoice add(Product product) {
        products.add(requireNonNull(product));
        return this;
    }

    public BigDecimal getTotalVAT() {
        return products.stream().map(Product::getVAT).reduce(BigDecimal::add).get();
    }

    public BigDecimal getTotalAmountWithVAT() {
        return products.stream().map(Product::getPriceWithVAT).reduce(BigDecimal::add).get();
    }

    public String toString() {
        return products.stream().map(Product::toString).collect(joining("\n"))
                + "Montant des taxes : " + getTotalVAT() + "\n"
                + "Total : " + getTotalAmountWithVAT() + "\n";
    }
}