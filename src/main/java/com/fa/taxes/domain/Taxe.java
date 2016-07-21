package com.fa.taxes.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Taxe {

    public static final BigDecimal _0_Percent = BigDecimal.ZERO;
    public static final BigDecimal _5_Percent = new BigDecimal("0.05");
    public static final BigDecimal _10_Percent = new BigDecimal("0.1");

    BigDecimal price;
    BigDecimal rate;
    BigDecimal importedRate;

    public Taxe(BigDecimal price, BigDecimal rate, BigDecimal importedRate) {
        this.price = Objects.requireNonNull(price);
        this.rate = Objects.requireNonNull(rate);
        this.importedRate = Objects.requireNonNull(importedRate);
    }

    public BigDecimal getVAT() {
        return round(price.multiply(rate))
                .add(round(price.multiply(importedRate)));
    }

    private static final BigDecimal ROUND = new BigDecimal("0.05");
    private static final BigDecimal _0_00 = new BigDecimal("0.00");

    public static BigDecimal round(BigDecimal amount) {
        BigDecimal value = amount.setScale(2, RoundingMode.CEILING);
        BigDecimal reminder = value.remainder(ROUND);
        return reminder.equals(_0_00) ? value : value.add(ROUND.subtract(reminder));
    }
}
