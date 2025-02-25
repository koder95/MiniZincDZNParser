package pl.koder95.dznp.interpreter;

import pl.koder95.dznp.core.Data;
import pl.koder95.dznp.core.IParser;

import java.io.*;

public class Interpreter implements pl.koder95.interpreter.Interpreter<Context, Data>, IParser {

    public static final Parser PARSER = new Parser();

    private final Context context;

    public Interpreter(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public Parser getParser() {
        return PARSER;
    }

    @Override
    public Data load(InputStream input) {
        return interpret(new InputStreamReader(input));
    }

    @Override
    public void save(OutputStream output, Data dzn) {
        throw new UnsupportedOperationException("Interpreter does not implement saving operation");
    }
}
