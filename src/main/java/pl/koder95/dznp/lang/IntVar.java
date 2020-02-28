package pl.koder95.dznp.lang;

public class IntVar extends Var<Integer> {

    IntVar(String name, int value) {
        super(Type.INT, name, value);
    }

    IntVar(String name) {
        this(name, 0);
    }

    @Override
    public void setValue(Integer value) {
        super.setValue(value);
    }

    @Override
    public Integer getValue() {
        return super.getValue();
    }
}
