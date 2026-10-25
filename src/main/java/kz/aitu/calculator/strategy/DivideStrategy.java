package kz.aitu.calculator.strategy;

public final class DivideStrategy implements OperationStrategy {

    @Override
    public double calculate(double dividend, double divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("cannot divide by zero");
        }
        return dividend / divisor;
    }
}
