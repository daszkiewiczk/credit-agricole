package pl.creditagricole.devopsninjabackend.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class MathUtils {
    public static final MathContext MATH_CONTEXT = new MathContext(100, RoundingMode.HALF_EVEN);


    public static final BigDecimal HUNDRED = BigDecimal.valueOf(100);
    public static final BigDecimal TWELVE = BigDecimal.valueOf(12);
    public static final BigDecimal FOUR = BigDecimal.valueOf(4);
}
