package com.fa.taxes.domain;

import java.math.BigDecimal;

import static com.fa.taxes.domain.Taxe._0_Percent;
import static com.fa.taxes.domain.Taxe._5_Percent;

public enum Origin {

    IMPORTED {
        @Override
        public BigDecimal getVATRate() {
            return _5_Percent;
        }
    },
    DOMESTIC {
        @Override
        public BigDecimal getVATRate() {
            return _0_Percent;
        }
    };

    public abstract BigDecimal getVATRate();
}