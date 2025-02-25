package pl.koder95.dznp.interpreter;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class Context implements pl.koder95.interpreter.Context {

    private final Map<String, Object> variables = new LinkedHashMap<>();

    public void initVariable(String name, Object value) {
        variables.put(name, value);
    }

    public void consumeVariables(Consumer<Map.Entry<String, Object>> consumer) {
        Set<Map.Entry<String, Object>> toConsume = new LinkedHashSet<>(variables.entrySet());
        variables.clear();
        toConsume.forEach(consumer);
    }
}
