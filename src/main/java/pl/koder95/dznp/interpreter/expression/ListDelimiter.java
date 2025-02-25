package pl.koder95.dznp.interpreter.expression;

public enum ListDelimiter {

    LEFT_PARENTHESIS('(', true), RIGHT_PARENTHESIS(')', true),
    LEFT_CURLY_BRACKET('{', true), RIGHT_CURLY_BRACKET('}', true),
    DOLAR_SIGN('$', true), COMMA(',', false), DOT('.', false),
    SEMICOLON(';', false), EQUALS_SIGN('=', false);

    private final char sign;
    private final boolean outer;

    ListDelimiter(char sign, boolean outer) {
        this.sign = sign;
        this.outer = outer;
    }

    public char getSign() {
        return sign;
    }

    public boolean isOuter() {
        return outer;
    }
}
