package com.fa.taxes.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;

public class Invoice {

    private List<Product> products = new ArrayList<>();

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
        return new StringJoiner("\n")
                .add(products.stream().map(Product::toString).collect(joining("\n")))
                .add("Montant des taxes : " + getTotalVAT())
                .add("Total : " + getTotalAmountWithVAT())
                .toString();
    }
}