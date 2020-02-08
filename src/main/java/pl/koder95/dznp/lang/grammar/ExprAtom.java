package pl.koder95.dznp.lang.grammar;

import pl.koder95.dznp.util.Stacks;

import java.io.IOException;
import java.util.Objects;
import java.util.Stack;

public class ExprAtom<T> {

    private final ExprAtomHead<T> head;
    private final ExprAtomTail<T> tail;
    private final Annotations annotations;

    public ExprAtom(ExprAtomHead<T> head, ExprAtomTail<T> tail, Annotations annotations) {
        this.head = Objects.requireNonNull(head);
        this.tail = Objects.requireNonNull(tail);
        this.annotations = Objects.requireNonNull(annotations);
    }

    public ExprAtomHead<T> getHead() {
        return head;
    }

    public ExprAtomTail<T> getTail() {
        return tail;
    }

    public Annotations getAnnotations() {
        return annotations;
    }

    public T asObject() {
        T obj = head.asObject();
        if (obj == null) obj = tail.asObject();
        return obj;
    }

    public static ExprAtom<?> from(Stack<Object> stack) throws IOException {
        Annotations a = Stacks.getType(stack, Annotations.class);
        if (a == null) a = Annotations.from(stack);
        if (a == null) throw new IOException();
        ExprAtomTail<?> tail = Stacks.getType(stack, ExprAtomTail.class);
        if (tail == null) tail = ExprAtomTail.from(stack);
        if (tail == null) Stacks.returnToStackAndThrow(stack, new IOException(), a);
        ExprAtomHead<?> head = Stacks.getType(stack, ExprAtomHead.class);
        if (head == null) head = ExprAtomHead.from(stack);
        if (head == null) Stacks.returnToStackAndThrow(stack, new IOException(), tail, a);
        return new ExprAtom(head, tail, a);
    }
}
