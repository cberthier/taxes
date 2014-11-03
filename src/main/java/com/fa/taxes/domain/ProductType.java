package com.fa.taxes.domain;

import java.math.BigDecimal;

public enum ProductType {

    BOOK {
        @Override
        public BigDecimal getVATRate() {
            return BigDecimal.ZERO;
        }
    },
    FOOD {
        @Override
        public BigDecimal getVATRate() {
            return BigDecimal.ZERO;
        }
    },
    DRUG {
        @Override
        public BigDecimal getVATRate() {
            return BigDecimal.ZERO;
        }
    },

    OTHER;

    public BigDecimal getVATRate() {
        return new BigDecimal("0.1");
    }
}
