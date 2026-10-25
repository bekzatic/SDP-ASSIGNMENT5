package kz.aitu.calculator.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class OperationStrategiesTest {

    @Test
    void addStrategyAddsTwoNumbers() {
        assertEquals(16, new AddStrategy().calculate(12, 4));
    }

    @Test
    void subtractStrategySubtractsTheRightNumberFromTheLeft() {
        assertEquals(-3, new SubtractStrategy().calculate(7, 10));
    }

    @Test
    void multiplyStrategyMultipliesTwoNumbers() {
        assertEquals(10, new MultiplyStrategy().calculate(2.5, 4));
    }

    @Test
    void divideStrategyDividesTheLeftNumberByTheRight() {
        assertEquals(3, new DivideStrategy().calculate(12, 4));
    }

    @Test
    void divideStrategyRefusesToDivideByZero() {
        assertThrows(ArithmeticException.class, () -> new DivideStrategy().calculate(9, 0));
    }
}
