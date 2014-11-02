package com.fa.taxes

import com.fa.taxes.domain.Invoice
import com.fa.taxes.domain.Item
import spock.lang.Shared
import spock.lang.Specification

class TaxesTest extends Specification {

    @Shared Invoice invoice1 = new Invoice();

    def "Use case 1"() {
        when:
        def iItem = new Item(name, price)

        then:
        iItem.VATPrice == priceWithVAT
        invoice1.addItem(price, priceWithVAT)

        where:
        quantity | name                |  price | priceWithVAT
        1        | "livre"             |  12.49 |        12.49
        1        | "CD musical"        |  14.99 |        16.49
        1        | "barre de chocolat" |   0.85 |         0.85
    }

    def "Use case 1 sums"() {
        expect:
        invoice1.totalVAT == 1.50
        invoice1.totalAmountWithVAT == 29.83
    }


    @Shared Invoice invoice2 = new Invoice();

    def "Use case 2"() {
        when:
        def iItem = new Item(name, price)

        then:
        iItem.VATPrice == priceWithVAT
        invoice2.addItem(price, priceWithVAT)

        where:
        quantity | name                           |  price | priceWithVAT
        1        | "boite de chocolats importée"  |  10.00 |        10.50
        1        | "flacon de parfum importé"     |  47.50 |        54.65
    }

    def "Use case 2 sums"() {
        expect:
        invoice2.totalVAT == 7.65
        invoice2.totalAmountWithVAT == 65.15
    }


    @Shared Invoice invoice3 = new Invoice();

    def "Use case 3"() {
        when:
        def iItem = new Item(name, price)

        then:
        iItem.VATPrice == priceWithVAT
        invoice3.addItem(price, priceWithVAT)

        where:
        quantity | name                                  |  price | priceWithVAT
        1        | "flacon de parfum importé"            |  27.99 |        32.19
        1        | "flacon de parfum"                    |  18.99 |        20.89
        1        | "boîte de pilules contre la migraine" |  9.75  |         9.75
        1        | "boîte de chocolats importés"         |  11.25 |        11.85
    }

    def "Use case 3 sums"() {
        expect:
        invoice3.totalVAT == 6.70
        invoice3.totalAmountWithVAT == 74.68
    }

}  