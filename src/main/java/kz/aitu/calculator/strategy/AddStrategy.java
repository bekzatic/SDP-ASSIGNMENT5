package kz.aitu.calculator.strategy;

public final class AddStrategy implements OperationStrategy {

    @Override
    public double calculate(double left, double right) {
        return left + right;
    }
}
