package pl.koder95.dznp.lang;

public class BoolVar extends Var<Boolean> {

    public BoolVar(String name, Boolean value) {
        super(name, value);
    }

    public BoolVar(String name) {
        this(name, false);
    }

    @Override
    public void setValue(Boolean value) {
        super.setValue(value);
    }
}
