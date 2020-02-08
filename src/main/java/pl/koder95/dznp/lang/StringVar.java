package pl.koder95.dznp.lang;

public class StringVar extends Var<String> {

    StringVar(String name, String value) {
        super(Type.STRING, name, value);
    }

    StringVar(String name) {
        this(name, "");
    }

    @Override
    public void setValue(String value) {
        super.setValue(value);
    }

    @Override
    public String getValue() {
        return super.getValue();
    }
}
