package pl.koder95.dznp.lang.grammar;

import pl.koder95.dznp.core.DataBuilder;
import pl.koder95.dznp.util.Stacks;

import java.io.IOException;
import java.util.Objects;
import java.util.Stack;

public class AssignItem {

    private final Ident ident;
    private final Expr<?> expr;

    private AssignItem(Ident ident, Expr<?> expr) {
        this.ident = Objects.requireNonNull(ident);
        this.expr = Objects.requireNonNull(expr);
    }

    public Ident getIdent() {
        return ident;
    }

    public Expr<?> getExpr() {
        return expr;
    }

    @Override
    public String toString() {
        return "Assign " +
                "object " + expr.asObject() + " " +
                "to ident " + ident +
                '.';
    }

    public void addToBuilder(DataBuilder builder) {
        builder.put(getIdent().getValue(), getExpr().asObject());
    }

    public static AssignItem from(Stack<Object> stack) throws IOException {
        Expr<?> expr = Stacks.getType(stack, Expr.class);
        if (expr == null) throw new IOException();
        String equivalent = Stacks.getType(stack, String.class);
        if (equivalent == null || !equivalent.equals("=")) throw new IOException();
        Ident ident = Stacks.getType(stack, Ident.class);
        if (ident == null) throw new IOException();
        return new AssignItem(ident, expr);
    }
}
