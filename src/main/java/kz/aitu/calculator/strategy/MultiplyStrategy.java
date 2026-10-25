package kz.aitu.calculator.strategy;

public final class MultiplyStrategy implements OperationStrategy {

    @Override
    public double calculate(double left, double right) {
        return left * right;
    }
}
