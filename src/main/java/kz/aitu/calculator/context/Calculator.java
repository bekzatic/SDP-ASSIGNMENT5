package kz.aitu.calculator.context;

import java.util.Objects;
import kz.aitu.calculator.strategy.OperationStrategy;

public final class Calculator {

    private OperationStrategy strategy;

    public Calculator(OperationStrategy initialStrategy) {
        setStrategy(initialStrategy);
    }

    public void setStrategy(OperationStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "strategy must not be null");
    }

    public double calculate(double left, double right) {
        return strategy.calculate(left, right);
    }
}
