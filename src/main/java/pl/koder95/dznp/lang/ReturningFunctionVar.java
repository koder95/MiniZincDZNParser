package pl.koder95.dznp.lang;

public class ReturningFunctionVar extends Var<Object> {

    private ReturningFunction returningFunction;

    ReturningFunctionVar(String name, ReturningFunction returningFunction) {
        super(Type.RETURNING_FUNCTION, name, null);
        this.returningFunction = returningFunction;
    }

    ReturningFunctionVar(String name) {
        this(name, null);
    }

    public ReturningFunction getReturningFunction() {
        return returningFunction;
    }

    public void setReturningFunction(ReturningFunction returningFunction) {
        this.returningFunction = returningFunction;
    }

    @Override
    public Object getValue() {
        setValue(returningFunction.call());
        return super.getValue();
    }
}
