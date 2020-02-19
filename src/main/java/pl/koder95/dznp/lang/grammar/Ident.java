package pl.koder95.dznp.lang.grammar;

import pl.koder95.dznp.util.Stacks;

import java.io.IOException;
import java.util.Stack;
import java.util.regex.Pattern;

public class Ident {

    private static final Pattern[] PATTERNS = {
            Pattern.compile("[A-Za-z][A-Za-z0-9_]*"),
            Pattern.compile("[^'\\xa0\\xd0\\x00]*")
    };
    private final String value;

    private Ident(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Ident(" + value + ')';
    }

    public static Ident from(Stack<Object> stack) throws IOException {
        String top = Stacks.getType(stack, String.class);
        if (top == null) throw new IOException();
        if (matches(top)) return new Ident(top);
        throw new IOException();
    }

    private static boolean matches(String str) {
        for (Pattern p : PATTERNS) {
            if (p.matcher(str).matches()) return true;
        }
        return false;
    }
}
