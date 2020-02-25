package pl.koder95.dznp.lang;

import java.util.ArrayList;

public class Array2DVar<E> extends ArrayVar<ArrayList<E>> {

    public Array2DVar(String name, ArrayList<ArrayList<E>> value) {
        super(name, value);
    }

    public Array2DVar(String name) {
        this(name, new ArrayList<>());
    }

    @Override
   public void setValue(ArrayList<ArrayList<E>> value) {
        super.setValue(value);
    }

    @Override
    public ArrayList<ArrayList<E>> getValue() {
        return super.getValue();
    }
}
