package pl.koder95.dznp.lang;

import java.util.ArrayList;

public abstract class ReturningFunction extends Function {

    public ReturningFunction(String name, Namespace parameters) {
        super(name, parameters);
    }

    public ReturningFunction(String name) {
        super(name);
    }

    public Object call() {
        ArrayList<Var> paramList = new ArrayList<>();
        String paramNameBegin = "param";
        int id = 1;
        Var current = null;
        do {
            paramList.add(getParameters().get(paramNameBegin + id));
        } while (current != null);
        return call(paramList.toArray(new Var[paramList.size()]));
    }

    public abstract Object call(Var[] params);
}
