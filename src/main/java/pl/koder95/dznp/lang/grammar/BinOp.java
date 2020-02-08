package pl.koder95.dznp.lang.grammar;

import pl.koder95.dznp.util.Stacks;

import java.io.IOException;
import java.util.Objects;
import java.util.Stack;

public class BinOp<T> {
    private static BinOp<Ident> create(Ident ident) {
        if (ident.isQuoted()) return new BinOp<>(ident);
        return null;
    }

    private static BinOp<BuiltinBinOp> create(BuiltinBinOp op) {
        return new BinOp<>(op);
    }

    private final T content;

    private BinOp(T content) {
        this.content = Objects.requireNonNull(content);
    }

    public T getContent() {
        return content;
    }

    public static BinOp<?> from(Stack<Object> stack) throws IOException {
        Ident ident = Stacks.getType(stack, Ident.class);
        if (ident == null) {
            try {
                ident = Ident.from(stack);
                if (!ident.isQuoted()) ident = null;
            } catch (IOException e) {
                ident = null;
            }
        }
        if (ident == null) {
            BuiltinBinOp op = Stacks.getType(stack, BuiltinBinOp.class);
            if (op == null) op = BuiltinBinOp.from(stack);
            return create(op);
        } else
            return create(ident);
    }
}
