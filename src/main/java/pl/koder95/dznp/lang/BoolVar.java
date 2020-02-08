package pl.koder95.dznp.lang;

public class BoolVar extends Var<Boolean> {

    BoolVar(String name, Boolean value) {
        super(Type.BOOL, name, value);
    }

    BoolVar(String name) {
        this(name, false);
    }

    @Override
    public void setValue(Boolean value) {
        super.setValue(value);
    }

    @Override
    public Boolean getValue() {
        return super.getValue();
    }
}
