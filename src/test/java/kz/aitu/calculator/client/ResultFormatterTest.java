package kz.aitu.calculator.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ResultFormatterTest {

    private final ResultFormatter formatter = new ResultFormatter();

    @Test
    void showsWholeNumbersWithoutDecimalZeros() {
        assertEquals("12 / 4 = 3", formatter.format(new Expression(12, "/", 4), 3.0));
    }

    @Test
    void keepsTheFractionalPartWhenThereIsOne() {
        assertEquals("5 / 2 = 2.5", formatter.format(new Expression(5, "/", 2), 2.5));
    }

    @Test
    void showsInfinityInsteadOfFailing() {
        assertEquals("2 * 3 = Infinity",
                formatter.format(new Expression(2, "*", 3), Double.POSITIVE_INFINITY));
    }
}
