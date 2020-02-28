package pl.koder95.dznp;

import pl.koder95.dznp.core.Data;
import pl.koder95.dznp.core.IParser;
import pl.koder95.dznp.lang.Namespace;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DZNParser implements IParser {

    @Override
    public Data load(InputStream input) throws IOException {
        Queue<String> tokens = createTokensQueue(input);
        if (tokens == null || tokens.isEmpty()) throw new ParserException("Cannot read tokens!");
        Stack<Object> stack = buildStack(tokens);
        if (stack == null || stack.empty()) throw new ParserException("Cannot build interpret stack!");
        Namespace ns = interpret(stack);
        if (ns == null) throw new ParserException("Cannot interpret a stack!");
        return ns.toData();
    }

    private Namespace interpret(Stack<Object> stack) throws ParserException {
        return new Interpreter(stack).interpret();
    }

    private Stack<Object> buildStack(Queue<String> tokens) {
        Stack<Object> stack = new Stack<>();
        while(!tokens.isEmpty()) {
            String token = tokens.poll();
            if (token.isEmpty() || token.equals(" ")) continue;
            stack.push(token);
        }
        return stack;
    }

    private Queue<String> createTokensQueue(InputStream input) throws IOException {
        LinkedList<String> tokens = new LinkedList<>();
        while (input.available() > 0) {
            Skipper skipper = new Skipper(input);

            int read = skipper.getLastRead();
            if (skipper.getSkipped() > 0) tokens.add(" ");
            else if (input.available() > 0) {
                String lastToken = tokens.pollLast();
                if (lastToken == null) lastToken = "";
                else if (lastToken.equals(" ")) {
                    tokens.addLast(lastToken);
                    lastToken = "";
                }
                boolean matches = matches(lastToken, read);
                if (matches) lastToken += read;
                tokens.addLast(lastToken);
                if (!matches) tokens.addLast("" + (char) read);
            }
        }
        return tokens;
    }

    private boolean matches(String lastToken, int read) {
        if (lastToken.isEmpty()) return true;
        char last = lastToken.charAt(lastToken.length()-1);
        return Character.getType(last) == Character.getType(read);
    }

    @Override
    public void save(OutputStream output, Data dzn) throws IOException {

    }
}
