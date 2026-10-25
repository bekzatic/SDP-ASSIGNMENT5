package kz.aitu.calculator.client;

import java.math.BigDecimal;

public final class ResultFormatter {

    public String format(Expression expression, double result) {
        return formatNumber(expression.left()) + " " + expression.operator() + " "
                + formatNumber(expression.right()) + " = " + formatNumber(result);
    }

    private String formatNumber(double number) {
        if (!Double.isFinite(number)) {
            return String.valueOf(number);
        }
        return BigDecimal.valueOf(number).stripTrailingZeros().toPlainString();
    }
}
