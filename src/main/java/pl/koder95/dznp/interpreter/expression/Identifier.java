package pl.koder95.dznp.interpreter.expression;

import pl.koder95.interpreter.NonTerminalExpression;

import java.util.regex.Pattern;

public class Identifier implements NonTerminalExpression<String> {

    private static final String PATTERN = "[a-zA-Z_][a-zA-Z0-9_]*";

    private final String name;

    private Identifier(String name) {
        if (!Pattern.matches(PATTERN, name)) throw new IllegalArgumentException();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getObject() {
        return name;
    }
}
