package pl.koder95.dznp.lang;

import java.util.ArrayList;

public class ArrayVar<E> extends Var<ArrayList<E>> {

    public ArrayVar(String name, ArrayList<E> value) {
        super(name, value);
    }

    public ArrayVar(String name) {
        this(name, new ArrayList());
    }
}
