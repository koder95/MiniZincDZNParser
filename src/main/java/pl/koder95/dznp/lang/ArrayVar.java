package pl.koder95.dznp.lang;

import java.util.ArrayList;

public class ArrayVar<E> extends Var<ArrayList<E>> {

    ArrayVar(Type type, String name, ArrayList<E> value) {
        super(type, name, value);
    }

    ArrayVar(String name, ArrayList<E> value) {
        this(Type.ARRAY, name, value);
    }

    ArrayVar(String name) {
        this(name, new ArrayList());
    }

    @Override
    public void setValue(ArrayList<E> value) {
        super.setValue(value);
    }

    @Override
    public ArrayList<E> getValue() {
        return super.getValue();
    }
}
