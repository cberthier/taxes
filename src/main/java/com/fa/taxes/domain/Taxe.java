package com.fa.taxes.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Taxe {

    private static final BigDecimal ROUND = new BigDecimal("0.05");

    BigDecimal price;
    BigDecimal rate;
    BigDecimal importedRate;

    public Taxe(BigDecimal price, BigDecimal rate, BigDecimal importedRate) {
        this.price = Objects.requireNonNull(price);
        this.rate = Objects.requireNonNull(rate);
        this.importedRate = Objects.requireNonNull(importedRate);
    }

    public static BigDecimal round(BigDecimal value) {
        value = value.setScale(2, RoundingMode.CEILING);
        BigDecimal reminder = value.remainder(ROUND);
        if (reminder.equals(new BigDecimal("0.00")))
            return value;
        return value.add(ROUND.subtract(reminder));
    }

    public BigDecimal getVAT() {
        return round(price.multiply(rate))
                .add(round(price.multiply(importedRate)));
    }
}
