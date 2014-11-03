package com.fa.taxes.domain;

import org.apache.tapestry5.func.Reducer;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.ZERO;


public class Product {

    private static BigDecimal importedTaxe = new BigDecimal("0.05");
    public static boolean IMPORTED = true;
    public static boolean NOT_IMPORTED = false;

    private String name;
    private ProductType productType;
    private boolean imported;
    private BigDecimal price;

    Product(String name, ProductType productType, boolean imported, BigDecimal price) {
        assert name != null;
        assert productType != null;
        this.name = name;
        this.productType = productType;
        this.imported = imported;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPriceWithVAT() {
        return price.add(getVAT());
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getVAT() {
        return round(price.multiply(productType.getVATRate()))
                .add(round(price.multiply(getImportTaxeRate())));
    }

    private BigDecimal getImportTaxeRate() {
        return imported ? importedTaxe : ZERO;
    }

    private static final BigDecimal ROUND = new BigDecimal("0.05");

    public static BigDecimal round(BigDecimal value) {
        value = value.setScale(2, RoundingMode.CEILING);
        BigDecimal reminder = value.remainder(ROUND);
        if (reminder.equals(new BigDecimal("0.00")))
                return value;
        return value.add(ROUND.subtract(reminder));
    }

    static Reducer<BigDecimal, Product> toTotalVAT = new Reducer<BigDecimal, Product>() {
        @Override
        public BigDecimal reduce(BigDecimal accumulator, Product product) {
            return accumulator.add(product.getVAT());
        }
    };
    static Reducer<BigDecimal, Product> toTotalPriceWithVAT = new Reducer<BigDecimal, Product>() {
        @Override
        public BigDecimal reduce(BigDecimal accumulator, Product product) {
            return accumulator.add(product.getPriceWithVAT());
        }
    };


}
