package pl.koder95.dznp.lang;

public class FloatVar extends Var<Float> {

    public FloatVar(String name, Float value) {
        super(name, value);
    }

    public FloatVar(String name) {
        this(name, 0f);
    }
}
