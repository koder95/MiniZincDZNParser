package pl.koder95.dznp.lang;

import java.util.HashSet;
import java.util.Set;

public class SetVar<E> extends Var<Set<E>> {

    public SetVar(String name, Set<E> value) {
        super(name, value);
    }

    public SetVar(String name) {
        this(name, new HashSet());
    }
}
