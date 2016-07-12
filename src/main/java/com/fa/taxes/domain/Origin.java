package com.fa.taxes.domain;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

public enum Origin {
    IMPORTED {
        @Override
        public BigDecimal getVATRate() {
            return new BigDecimal("0.05");
        }
    },
    DOMESTIC {
        @Override
        public BigDecimal getVATRate() {
            return ZERO;
        }
    };

    public abstract BigDecimal getVATRate();
}