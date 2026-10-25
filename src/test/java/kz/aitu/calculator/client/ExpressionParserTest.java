package kz.aitu.calculator.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ExpressionParserTest {

    private final ExpressionParser parser = new ExpressionParser();

    @Test
    void readsNumberOperatorNumber() {
        Expression expected = new Expression(12, "*", 4);

        assertEquals(expected, parser.parse("  12   *  4 ").orElseThrow());
    }

    @Test
    void rejectsTextWithTheWrongNumberOfParts() {
        assertTrue(parser.parse("12 *").isEmpty());
    }

    @Test
    void rejectsSomethingThatIsNotANumber() {
        assertTrue(parser.parse("twelve * 4").isEmpty());
    }
}
