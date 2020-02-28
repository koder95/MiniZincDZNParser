package pl.koder95.dznp.lang;

import java.util.Objects;

public class Function extends NamedObject {

    private final Parameters parameters;

    public Function(String name, Parameters parameters) {
        super(name);
        this.parameters = Objects.requireNonNull(parameters);
    }

    public Function(String name) {
        this(name, new Parameters());
    }

    public Parameters getParameters() {
        return parameters;
    }
}
