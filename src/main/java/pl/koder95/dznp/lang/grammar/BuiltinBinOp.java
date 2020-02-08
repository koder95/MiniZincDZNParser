package pl.koder95.dznp.lang.grammar;

import pl.koder95.dznp.util.Stacks;

import java.io.IOException;
import java.util.Stack;

public enum BuiltinBinOp {
    BBO_00("<->"), BBO_01("->"), BBO_02("<-"), BBO_03("\\/"), BBO_04("xor"),
    BBO_05("/\\"), BBO_06("<"), BBO_07(">"), BBO_08("<="), BBO_09(">="),
    BBO_10("=="), BBO_11("="), BBO_12("in"), BBO_13("subset"),
    BBO_14("superset"), BBO_15("union"), BBO_16("diff"), BBO_17("symdiff"),
    BBO_18(".."), BBO_19("intersect"), BBO_20("++"), BBNO_00("+"),
    BBNO_01("-"), BBNO_02("*"), BBNO_03("/"), BBNO_04("div"),
    BBNO_05("mod");

    private final String operator;

    BuiltinBinOp(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public boolean isNum() {
        return name().contains("N");
    }

    public static BuiltinBinOp from(Stack<Object> stack) throws IOException {
        String s = Stacks.getType(stack, String.class);
        if (s == null) throw new IOException();
        for (BuiltinBinOp bbo : values()) {
            if (s.equals(bbo.operator)) return bbo;
        }
        return null;
    }
}
