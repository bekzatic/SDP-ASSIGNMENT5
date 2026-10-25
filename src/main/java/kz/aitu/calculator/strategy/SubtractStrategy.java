package kz.aitu.calculator.strategy;

public final class SubtractStrategy implements OperationStrategy {

    @Override
    public double calculate(double left, double right) {
        return left - right;
    }
}
