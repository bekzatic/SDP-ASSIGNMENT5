package kz.aitu.calculator.client;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import kz.aitu.calculator.strategy.OperationStrategy;

public final class StrategyCatalog {

    private final Map<String, OperationStrategy> strategiesBySymbol = new LinkedHashMap<>();

    public StrategyCatalog register(String symbol, OperationStrategy strategy) {
        strategiesBySymbol.put(symbol, Objects.requireNonNull(strategy, "strategy must not be null"));
        return this;
    }

    public Optional<OperationStrategy> find(String symbol) {
        return Optional.ofNullable(strategiesBySymbol.get(symbol));
    }

    public Map<String, OperationStrategy> all() {
        return Collections.unmodifiableMap(strategiesBySymbol);
    }
}
