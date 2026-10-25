package kz.aitu.calculator.client;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import kz.aitu.calculator.context.Calculator;
import kz.aitu.calculator.strategy.AddStrategy;
import kz.aitu.calculator.strategy.DivideStrategy;
import kz.aitu.calculator.strategy.MultiplyStrategy;
import org.junit.jupiter.api.Test;

class CalculatorConsoleTest {

    private final ByteArrayOutputStream printed = new ByteArrayOutputStream();

    @Test
    void switchesToTheStrategyTheUserAsksFor() {
        runWithInput("12 * 4\nquit\n");

        assertTrue(output().contains("[MultiplyStrategy] 12 * 4 = 48"));
    }

    @Test
    void reportsDivisionByZeroInsteadOfCrashing() {
        runWithInput("9 / 0\nquit\n");

        assertTrue(output().contains("[DivideStrategy] Error: cannot divide by zero"));
    }

    @Test
    void reportsAnUnknownOperator() {
        runWithInput("5 % 2\nquit\n");

        assertTrue(output().contains("Unknown operator '%'"));
    }

    @Test
    void explainsTheExpectedFormatForUnreadableInput() {
        runWithInput("hello\nquit\n");

        assertTrue(output().contains("Please type: number operator number"));
    }

    private void runWithInput(String typedLines) {
        StrategyCatalog catalog = new StrategyCatalog()
                .register("+", new AddStrategy())
                .register("*", new MultiplyStrategy())
                .register("/", new DivideStrategy());
        CalculatorConsole console = new CalculatorConsole(
                new Calculator(new AddStrategy()),
                catalog,
                new ExpressionParser(),
                new ResultFormatter(),
                new Scanner(typedLines),
                new PrintStream(printed, true, StandardCharsets.UTF_8));
        console.runInteractive();
    }

    private String output() {
        return printed.toString(StandardCharsets.UTF_8);
    }
}
