package pl.koder95.dznp.lang;

public class StringVar extends Var<String> {

    public StringVar(String name, String value) {
        super(name, value);
    }

    public StringVar(String name) {
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
