package com.fa.taxes

import spock.lang.Specification

import static com.fa.taxes.domain.Taxe.round

class RoundingTest extends Specification {

    def "Use case 1"() {
        expect:
        round(price) == roundedPrice

        where:
        price | roundedPrice
        0.99  | 1.00
        1.00  | 1.00
        1.27  | 1.30
        1.01  | 1.05
        1.02  | 1.05
    }
}
