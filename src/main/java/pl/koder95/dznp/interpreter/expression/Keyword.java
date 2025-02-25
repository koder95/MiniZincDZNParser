package pl.koder95.dznp.interpreter.expression;

import pl.koder95.interpreter.NonTerminalExpression;

public enum Keyword implements NonTerminalExpression<String> {

    BEHAVIOR, BLOCKING, BOOL, COMPONENT, DEFER, ELSE, EXTERN, EXTERNAL, ENUM, FALSE, IF, ILLEGAL, IMPORT, INEVITABLE,
    INJECTED, INOUT, INTERFACE, IN, NAMESPACE, ON, OPTIONAL, OTHERWISE, OUT, PROVIDES, REPLY, REQUIRES, RETURN, SUBINT,
    SYSTEM, TRUE;

    public String getSystemName() {
        return name().toLowerCase();
    }

    @Override
    public String getObject() {
        return getSystemName();
    }
}
