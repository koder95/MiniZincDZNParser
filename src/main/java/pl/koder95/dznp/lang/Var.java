package pl.koder95.dznp.lang;

import pl.koder95.dznp.util.Strings;

import java.util.Objects;

public class Var<T> extends NamedObject {

    private T value;

    Var(String name, T value) {
        super(name);
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    void setValue(T value) {
        this.value = value;
    }
}
