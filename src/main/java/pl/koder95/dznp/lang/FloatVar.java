package pl.koder95.dznp.lang;

public class FloatVar extends Var<Float> {

    FloatVar(String name, Float value) {
        super(Type.FLOAT, name, value);
    }

    FloatVar(String name) {
        this(name, 0f);
    }

    @Override
    public void setValue(Float value) {
        super.setValue(value);
    }

    @Override
    public Float getValue() {
        return super.getValue();
    }
}
