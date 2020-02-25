package pl.koder95.dznp.lang;

public class IntVar extends Var<Integer> {

    public IntVar(String name, int value) {
        super(name, value);
    }

    public IntVar(String name) {
        this(name, 0);
    }

    @Override
    public void setValue(Integer value) {
        super.setValue(value);
    }
}
