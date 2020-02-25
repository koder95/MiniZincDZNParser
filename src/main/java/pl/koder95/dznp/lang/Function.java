package pl.koder95.dznp.lang;


import java.util.ArrayList;
import java.util.Objects;

public class Function extends NamedObject {

    private final Namespace parameters;

    public Function(String name, Namespace parameters) {
        super(name);
        this.parameters = Objects.requireNonNull(parameters);
    }

    public Function(String name) {
        this(name, new Namespace());
    }

    public Namespace getParameters() {
        return parameters;
    }
}
