package pl.koder95.dznp.lang.grammar;

import pl.koder95.dznp.util.Stacks;

import java.util.Objects;
import java.util.Stack;

public class Expr<T> {

    private final ExprAtom<T> atom;
    private final ExprBinopTail<T> tail;

    private Expr(ExprAtom<T> atom, ExprBinopTail<T> tail) {
        this.atom = Objects.requireNonNull(atom);
        this.tail = Objects.requireNonNull(tail);
    }

    public static Expr<?> from(Stack<Object> stack) {
        ExprBinopTail<?> tail = Stacks.getType(stack, ExprBinopTail.class);
        if (tail == null) tail = ExprBinopTail.from(stack);
        ExprAtom<?> atom = Stacks.getType(stack, ExprAtom.class);
        if (atom == null) atom = ExprAtom.from(stack);
        return new Expr(atom, tail);
    }

    public T asObject() {
        return tail.asObject(atom);
    }
}
