package com.fa.taxes.domain;

import java.math.BigDecimal;

/**
 * //TODO : document me!
 */
public class Invoice {

    BigDecimal totalVAT = new BigDecimal(0);
    BigDecimal totalAmountWithVAT = new BigDecimal(0);

    public void addItem(BigDecimal price, BigDecimal priceWithVAT) {
        totalAmountWithVAT = totalAmountWithVAT.add(priceWithVAT);

        totalVAT = totalVAT.add((priceWithVAT.subtract(price)));
    }

}