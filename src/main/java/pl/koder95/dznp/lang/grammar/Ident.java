package pl.koder95.dznp.lang.grammar;

import pl.koder95.dznp.util.Stacks;

import java.io.IOException;
import java.util.Stack;
import java.util.regex.Pattern;

public class Ident {

    private static final Pattern NORMAL = Pattern.compile("[A-Za-z][A-Za-z0-9_]*");
    private static final Pattern QUOTED = Pattern.compile("'[^'\\xa0\\xd0\\x00]*'");

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
        if (top == null) {
            buildStringOnTop(stack);
            return from(stack);
        } else if(matches(top)) {
            return new Ident(top);
        } else if (top.equals("'")) {
            top = Stacks.getType(stack, String.class);
            String quote = Stacks.getType(stack, String.class);
            final String value = quote + top + quote;
            if (quote == null || !quote.equals("'")) throw new IOException();
            if (QUOTED.matcher(value).matches()) return new Ident(value);
            throw new IOException();
        } else Stacks.returnToStackAndThrow(stack, new IOException(), top);
        return null;
    }

    private static void buildStringOnTop(Stack<Object> stack) {
        StringBuilder builder = new StringBuilder();
        char first = 0;
        while (!stack.empty()) {
            Character c = Stacks.getType(stack, Character.class);
            if (c == null) break;
            if (first == 0) {
                first = c;
                if (first == '\'') {
                    builder.append(first);
                    continue;
                }
            }
            if (first == '\'' && c == first) {
                builder.append(c);
                break;
            }
            if (first != '\'' && Character.isSpaceChar(c)) break;
            builder.append(c);
            if (removeNonMatchesLastChar(stack, builder, first, c)) break;
        }
        stack.push(builder.reverse().toString());
    }

    private static boolean removeNonMatchesLastChar(Stack<Object> stack, StringBuilder builder, char first, char current) {
        String str = builder.toString();
        if (builder.charAt(0) == '\'' && builder.charAt(builder.length()-1) != '\'') str += '\'';
        if (builder.length() > 0 && !matches(str)) {
            builder.setLength(builder.length() - 1);
            stack.push(current);
            return true;
        }
        return false;
    }

    private static boolean quoted(String str) {
        return str.startsWith("'") && str.endsWith("'");
    }

    private static boolean matches(String str) {
        return quoted(str)? QUOTED.matcher(str).matches() : NORMAL.matcher(str).matches();
    }
}
