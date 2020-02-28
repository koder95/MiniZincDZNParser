package pl.koder95.dznp;

import pl.koder95.dznp.lang.Namespace;
import pl.koder95.dznp.util.Stacks;

import java.util.Objects;
import java.util.Stack;

public class Interpreter {

    private final Stack<Object> stack;

    public Interpreter(Stack<Object> stack) {
        this.stack = Objects.requireNonNull(Stacks.requireNonEmpty(stack));
    }

    public Namespace interpret() throws ParserException {
        Namespace ns = new Namespace();
        while (!stack.empty()) {
            // tworzenie nowych elementów w Namespace, ale narazie tylko drukowanie na standardowe wyjście

            System.out.println(stack.pop()); // TODO: uzupełnić pętlę, aby tworzyła nowe elementy
        }
        return ns;
    }
}
