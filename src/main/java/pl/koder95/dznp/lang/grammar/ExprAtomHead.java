package pl.koder95.dznp.lang.grammar;

import java.util.Set;
import java.util.Stack;

public abstract class ExprAtomHead<T> {
    public static ExprAtomHead<?> from(Stack<Object> stack) {
        return null;
    }

    public abstract T asObject();

    private static ExprAtomHead<?> create(BuiltinUnOp op, ExprAtom<?> atom) {
        return new BuiltinUnOpExprAtom(op, atom);
    }

    private static ExprAtomHead<?> create(Expr<?> expr) {
        return new ExprAtomHead<Object>() {
            @Override
            public Object asObject() {
                return expr.asObject();
            }
        };
    }

    private static ExprAtomHead<String> create() {
        return new ExprAtomHead<String>() {
            @Override
            public String asObject() {
                return "_";
            }
        };
    }

    private static ExprAtomHead<Boolean> create(BoolLiteral literal) {
        return new ExprAtomHead<Boolean>() {
            @Override
            public Boolean asObject() {
                return literal.getValue();
            }
        };
    }

    private static ExprAtomHead<Integer> create(IntLiteral literal) {
        return new ExprAtomHead<Integer>() {
            @Override
            public Integer asObject() {
                return literal.getValue();
            }
        };
    }

    private static ExprAtomHead<Float> create(FloatLiteral literal) {
        return new ExprAtomHead<Float>() {
            @Override
            public Float asObject() {
                return literal.getValue();
            }
        };
    }

    private static ExprAtomHead<String> create(StringLiteral literal) {
        return new ExprAtomHead<String>() {
            @Override
            public String asObject() {
                return literal.getValue();
            }
        };
    }

    private static ExprAtomHead<Set> create(IntLiteral literal) {
        return new ExprAtomHead<Integer>() {
            @Override
            public Integer asObject() {
                return literal.getValue();
            }
        };
    }

    private static ExprAtomHead<Integer> create(IntLiteral literal) {
        return new ExprAtomHead<Integer>() {
            @Override
            public Integer asObject() {
                return literal.getValue();
            }
        };
    }

    private static ExprAtomHead<Integer> create(IntLiteral literal) {
        return new ExprAtomHead<Integer>() {
            @Override
            public Integer asObject() {
                return literal.getValue();
            }
        };
    }

    private static ExprAtomHead<Integer> create(IntLiteral literal) {
        return new ExprAtomHead<Integer>() {
            @Override
            public Integer asObject() {
                return literal.getValue();
            }
        };
    }
}
