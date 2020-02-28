package pl.koder95.dznp.lang;

import java.util.HashSet;
import java.util.Set;

public class SetVar<E> extends Var<Set<E>> {

    SetVar(String name, Set<E> value) {
        super(Type.SET, name, value);
    }

    SetVar(String name) {
        this(name, new HashSet());
    }

    @Override
    void setValue(Set<E> value) {
        super.setValue(value);
    }

    @Override
    public Set<E> getValue() {
        return super.getValue();
    }
}
