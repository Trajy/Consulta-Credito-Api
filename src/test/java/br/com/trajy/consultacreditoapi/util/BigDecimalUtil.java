package br.com.trajy.consultacreditoapi.util;

import java.math.BigDecimal;

public final class BigDecimalUtil {

    private BigDecimalUtil() { }

    public static BigDecimal formatBigDecimalToJsonPathResultMatchers(BigDecimal value) {
         value = value.stripTrailingZeros();
        if(value.scale() < 1) {
            return value.setScale(1);
        }
        return value;
    }

}
