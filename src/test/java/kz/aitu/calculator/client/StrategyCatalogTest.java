package kz.aitu.calculator.client;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import kz.aitu.calculator.strategy.AddStrategy;
import kz.aitu.calculator.strategy.OperationStrategy;
import org.junit.jupiter.api.Test;

class StrategyCatalogTest {

    @Test
    void findsTheStrategyRegisteredForASymbol() {
        OperationStrategy add = new AddStrategy();
        StrategyCatalog catalog = new StrategyCatalog().register("+", add);

        assertSame(add, catalog.find("+").orElseThrow());
    }

    @Test
    void findsNothingForAnUnknownSymbol() {
        StrategyCatalog catalog = new StrategyCatalog().register("+", new AddStrategy());

        assertTrue(catalog.find("%").isEmpty());
    }
}
