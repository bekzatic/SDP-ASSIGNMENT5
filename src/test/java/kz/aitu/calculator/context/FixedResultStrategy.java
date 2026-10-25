package kz.aitu.calculator.context;

import kz.aitu.calculator.strategy.OperationStrategy;

final class FixedResultStrategy implements OperationStrategy {

    private final double fixedResult;

    FixedResultStrategy(double fixedResult) {
        this.fixedResult = fixedResult;
    }

    @Override
    public double calculate(double left, double right) {
        return fixedResult;
    }
}
