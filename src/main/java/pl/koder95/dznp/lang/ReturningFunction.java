package pl.koder95.dznp.lang;

import java.util.ArrayList;

public abstract class ReturningFunction extends Function {

    public ReturningFunction(String name, Parameters parameters) {
        super(name, parameters);
    }

    public ReturningFunction(String name) {
        super(name);
    }

    public Object call() {
        ArrayList<Var> paramList = new ArrayList<>();
        for (Var param : getParameters()) {
            if (param != null) paramList.add(param);
        }
        return call(paramList.toArray(new Var[paramList.size()]));
    }

    public abstract Object call(Var[] params);
}
