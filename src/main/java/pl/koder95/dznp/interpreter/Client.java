package pl.koder95.dznp.interpreter;

import pl.koder95.dznp.core.Data;

public class Client implements pl.koder95.interpreter.Client<Context, Data, Interpreter> {
    @Override
    public Interpreter newInterpreter(Context context) {
        return new Interpreter(context);
    }
}
