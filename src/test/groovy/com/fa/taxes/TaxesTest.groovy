package com.fa.taxes

import com.fa.taxes.domain.Invoice
import com.fa.taxes.domain.Product
import spock.lang.Shared
import spock.lang.Specification

import static com.fa.taxes.domain.Origin.DOMESTIC
import static com.fa.taxes.domain.Origin.IMPORTED
import static com.fa.taxes.domain.ProductType.*

class TaxesTest extends Specification {

    @Shared
    Invoice invoice1 = new Invoice();

    def "Use case 1"() {
        when:
        def product = new Product(name, productType, imported, price)

        then:
        product.priceWithVAT == priceWithVAT
        invoice1.add(product)

        where:
        quantity | name                | productType | imported | price | priceWithVAT
        1        | "livre"             | BOOK        | DOMESTIC | 12.49 | 12.49
        1        | "CD musical"        | OTHER       | DOMESTIC | 14.99 | 16.49
        1        | "barre de chocolat" | FOOD        | DOMESTIC | 0.85  | 0.85
    }

    def "Use case 1 sums"() {
        println invoice1
        expect:
        invoice1.totalVAT == 1.50
        invoice1.totalAmountWithVAT == 29.83
    }


    @Shared
    Invoice invoice2 = new Invoice();

    def "Use case 2"() {
        when:
        def product = new Product(name, productType, imported, price)

        then:
        product.priceWithVAT == priceWithVAT
        invoice2.add(product)

        where:
        quantity | name                          | productType | imported | price | priceWithVAT
        1        | "boite de chocolats importée" | FOOD        | IMPORTED | 10.00 | 10.50
        1        | "flacon de parfum importé"    | OTHER       | IMPORTED | 47.50 | 54.65
    }

    def "Use case 2 sums"() {
        println invoice2
        expect:
        invoice2.totalVAT == 7.65
        invoice2.totalAmountWithVAT == 65.15
    }


    @Shared
    Invoice invoice3 = new Invoice();

    def "Use case 3"() {
        when:
        def product = new Product(name, productType, imported, price)

        then:
        product.priceWithVAT == priceWithVAT
        invoice3.add(product)

        where:
        quantity | name                                  | productType | imported | price | priceWithVAT
        1        | "flacon de parfum importé"            | OTHER       | IMPORTED | 27.99 | 32.19
        1        | "flacon de parfum"                    | OTHER       | DOMESTIC | 18.99 | 20.89
        1        | "boîte de pilules contre la migraine" | DRUG        | DOMESTIC | 9.75  | 9.75
        1        | "boîte de chocolats importés"         | FOOD        | IMPORTED | 11.25 | 11.85
    }

    def "Use case 3 sums"() {
        println invoice3
        expect:
        invoice3.totalVAT == 6.70
        invoice3.totalAmountWithVAT == 74.68
    }

}  