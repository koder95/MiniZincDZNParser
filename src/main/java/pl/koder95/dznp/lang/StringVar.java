package pl.koder95.dznp.lang;

public class StringVar extends Var<String> {

    public StringVar(String name, String value) {
        super(name, value);
    }

    public StringVar(String name) {
        this(name, "");
    }

}
