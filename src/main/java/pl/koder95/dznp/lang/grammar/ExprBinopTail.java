package pl.koder95.dznp.lang.grammar;

import pl.koder95.dznp.util.Stacks;

import java.io.IOException;
import java.util.Stack;

public class ExprBinopTail<T> {
    private final BinOp<?> op;
    private final Expr<T> expr;

    private ExprBinopTail(BinOp<?> op, Expr<T> expr) {
        this.op = op;
        this.expr = expr;
    }

    public BinOp<?> getOp() {
        return op;
    }

    public Expr<T> getExpr() {
        return expr;
    }

    public static ExprBinopTail<?> from(Stack<Object> stack) throws IOException {
        Expr<?> expr = Stacks.getType(stack, Expr.class);
        BinOp<?> binOp = Stacks.getType(stack, BinOp.class);
        if (expr != null && binOp != null) return new ExprBinopTail<>(binOp, expr);
        else if (expr != null && binOp == null) Stacks.returnToStackAndThrow(stack, new IOException(), expr);
        else if (expr == null && binOp != null) Stacks.returnToStackAndThrow(stack, new IOException(), binOp);
        return new ExprBinopTail<>(null, null);
    }

    public T asObject(ExprAtom<T> atom) {
        T obj = atom.asObject();
        if (obj == null) obj = expr.asObject();
        return obj;
    }
}
