package pl.koder95.dznp.lang;

public abstract class ReturningFunction extends Function {

    public ReturningFunction(String name, Namespace parameters) {
        super(name, parameters);
    }

    public ReturningFunction(String name) {
        super(name);
    }

    public abstract Object call();
}
