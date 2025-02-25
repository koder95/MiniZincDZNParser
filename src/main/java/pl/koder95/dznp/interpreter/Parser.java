package pl.koder95.dznp.interpreter;

import pl.koder95.dznp.core.Data;
import pl.koder95.interpreter.NonTerminalExpression;
import pl.koder95.interpreter.TerminalExpression;

import java.util.Queue;

public class Parser implements pl.koder95.interpreter.Parser<Context, Data> {

    public static final Tokenizer TOKENIZER = new Tokenizer();

    @Override
    public Tokenizer getTokenizer() {
        return TOKENIZER;
    }

    @Override
    public TerminalExpression<Context, Data> buildAbstractSyntaxTree(Queue<NonTerminalExpression<?>> tokens) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
