package kz.aitu.calculator.client;

import java.util.Optional;

public final class ExpressionParser {

    private static final int PARTS_IN_EXPRESSION = 3;

    public Optional<Expression> parse(String text) {
        String[] parts = text.trim().split("\\s+");
        if (parts.length != PARTS_IN_EXPRESSION) {
            return Optional.empty();
        }
        try {
            double left = Double.parseDouble(parts[0]);
            double right = Double.parseDouble(parts[2]);
            return Optional.of(new Expression(left, parts[1], right));
        } catch (NumberFormatException notANumber) {
            return Optional.empty();
        }
    }
}
