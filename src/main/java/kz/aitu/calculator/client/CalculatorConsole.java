package kz.aitu.calculator.client;

import java.io.PrintStream;
import java.util.Scanner;
import kz.aitu.calculator.context.Calculator;
import kz.aitu.calculator.strategy.OperationStrategy;

public final class CalculatorConsole {

    private static final String QUIT_COMMAND = "quit";

    private final Calculator calculator;
    private final StrategyCatalog catalog;
    private final ExpressionParser parser;
    private final ResultFormatter formatter;
    private final Scanner input;
    private final PrintStream output;

    public CalculatorConsole(Calculator calculator, StrategyCatalog catalog, ExpressionParser parser,
                             ResultFormatter formatter, Scanner input, PrintStream output) {
        this.calculator = calculator;
        this.catalog = catalog;
        this.parser = parser;
        this.formatter = formatter;
        this.input = input;
        this.output = output;
    }

    public void runDemo(double left, double right) {
        output.println("== Same numbers, every strategy ==");
        catalog.all().forEach((symbol, strategy) ->
                calculateAndPrint(new Expression(left, symbol, right), strategy));
        output.println();
    }

    public void runInteractive() {
        output.println("Type an expression like 12 * 4, or '" + QUIT_COMMAND + "' to exit.");
        printPrompt();
        while (input.hasNextLine()) {
            String line = input.nextLine().trim();
            if (line.equalsIgnoreCase(QUIT_COMMAND)) {
                return;
            }
            parser.parse(line).ifPresentOrElse(
                    this::evaluate,
                    () -> output.println("Please type: number operator number, for example 12 * 4"));
            printPrompt();
        }
    }

    private void evaluate(Expression expression) {
        catalog.find(expression.operator()).ifPresentOrElse(
                strategy -> calculateAndPrint(expression, strategy),
                () -> output.println("Unknown operator '" + expression.operator()
                        + "'. Available: " + catalog.all().keySet()));
    }

    private void calculateAndPrint(Expression expression, OperationStrategy strategy) {
        calculator.setStrategy(strategy);
        String strategyName = strategy.getClass().getSimpleName();
        try {
            double result = calculator.calculate(expression.left(), expression.right());
            output.println("[" + strategyName + "] " + formatter.format(expression, result));
        } catch (ArithmeticException error) {
            output.println("[" + strategyName + "] Error: " + error.getMessage());
        }
    }

    private void printPrompt() {
        output.print("> ");
    }
}
