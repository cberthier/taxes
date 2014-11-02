package com.fa.taxes.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Pattern;

public class Item {

    private String name;
    private BigDecimal price;

    private static final BigDecimal TAXE = new BigDecimal("0.10");
    private static final BigDecimal IMPORT_TAXE = new BigDecimal("0.05");
    private static final BigDecimal ROUND = new BigDecimal("0.05");

    private static final Pattern NO_TAX_ITEM_PATTERN = Pattern.compile("livre.*|.*chocolat.*|boîte de pilules contre la migraine");
    private static final Pattern IMPORT_ITEM_PATTERN = Pattern.compile(".*importé[se]{0,1}");

    Item(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Calculate Item VAT
     *
     * @return Item VAT
     */
    BigDecimal getVATPrice() {
        BigDecimal VATPrice = new BigDecimal(price.toString());

        //If item is concerned by VAT, it's add to VAT price
        if (!NO_TAX_ITEM_PATTERN.matcher(name).matches())
            VATPrice = VATPrice.add(getVAT(TAXE));

        //If item is concerned by import VAT, it's add to VAT price
        if (IMPORT_ITEM_PATTERN.matcher(name).matches())
            VATPrice = VATPrice.add(getVAT(IMPORT_TAXE));

        return VATPrice;
    }

    /**
     * Rounding VAT at 5 cents higher
     * 0.99 => 1.00
     * 1.00 => 1.00
     * 1.01 => 1.05
     * 1.02 => 1.05
     *
     * @param rate VAT calculate
     * @return VAT rounded
     */
    private BigDecimal getVAT(BigDecimal rate) {
        BigDecimal vat = price.multiply(rate).setScale(2, RoundingMode.CEILING);
        BigDecimal reminder = vat.remainder(ROUND);
        if (reminder.equals(new BigDecimal("0.00")))
            return vat;

        return vat.add(ROUND.subtract(reminder));
    }
}