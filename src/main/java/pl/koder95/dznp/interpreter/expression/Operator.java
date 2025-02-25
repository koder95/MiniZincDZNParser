package pl.koder95.dznp.interpreter.expression;

public enum Operator {

    PLUS("+"), HYPHEN_MINUS("-"), EXCLAMATION_MARK("!"), LESS_THEN("<"), GREATER_THAN(">"),
    LESS_THAN_AND_EQUALS_SIGN("<="), GREATER_THAN_AND_EQUALS_SIGN(">="), DOUBLE_EQUALS_SIGN("=="),
    EXCLAMATION_MARK_AND_EQUALS_SIGN("!="), LESS_THAN_AND_EQUALS_SIGN_AND_GREATER_THAN("<=>");

    private final CharSequence characters;

    Operator(CharSequence characters) {
        this.characters = characters;
    }

    public CharSequence getCharacters() {
        return characters;
    }
}
