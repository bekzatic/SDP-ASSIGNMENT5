package kz.aitu.calculator.context;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    void delegatesTheCalculationToItsCurrentStrategy() {
        Calculator calculator = new Calculator(new FixedResultStrategy(42));

        double result = calculator.calculate(1, 2);

        assertEquals(42, result);
    }

    @Test
    void usesTheNewStrategyAfterSwitchingAtRuntime() {
        Calculator calculator = new Calculator(new FixedResultStrategy(42));

        calculator.setStrategy(new FixedResultStrategy(7));

        assertEquals(7, calculator.calculate(1, 2));
    }

    @Test
    void rejectsAMissingStrategy() {
        Calculator calculator = new Calculator(new FixedResultStrategy(42));

        assertThrows(NullPointerException.class, () -> calculator.setStrategy(null));
    }
}
