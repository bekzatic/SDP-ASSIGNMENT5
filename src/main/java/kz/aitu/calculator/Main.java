package kz.aitu.calculator;

import java.util.Scanner;
import kz.aitu.calculator.client.CalculatorConsole;
import kz.aitu.calculator.client.ExpressionParser;
import kz.aitu.calculator.client.ResultFormatter;
import kz.aitu.calculator.client.StrategyCatalog;
import kz.aitu.calculator.context.Calculator;
import kz.aitu.calculator.strategy.AddStrategy;
import kz.aitu.calculator.strategy.DivideStrategy;
import kz.aitu.calculator.strategy.MultiplyStrategy;
import kz.aitu.calculator.strategy.OperationStrategy;
import kz.aitu.calculator.strategy.SubtractStrategy;

public final class Main {

    private static final double DEMO_LEFT_NUMBER = 12;
    private static final double DEMO_RIGHT_NUMBER = 4;

    private Main() {
    }

    public static void main(String[] args) {
        OperationStrategy add = new AddStrategy();
        StrategyCatalog catalog = new StrategyCatalog()
                .register("+", add)
                .register("-", new SubtractStrategy())
                .register("*", new MultiplyStrategy())
                .register("/", new DivideStrategy());

        CalculatorConsole console = new CalculatorConsole(
                new Calculator(add),
                catalog,
                new ExpressionParser(),
                new ResultFormatter(),
                new Scanner(System.in),
                System.out);

        console.runDemo(DEMO_LEFT_NUMBER, DEMO_RIGHT_NUMBER);
        console.runInteractive();
    }
}
