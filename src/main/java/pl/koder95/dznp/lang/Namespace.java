package pl.koder95.dznp.lang;

import pl.koder95.dznp.core.Data;
import pl.koder95.dznp.core.DataBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Namespace {

    private final Map<String, Var> definedVariables = new HashMap<>();

    private boolean add(Var var) {
        Objects.requireNonNull(var);
        if (!definedVariables.containsKey(var.getName())) {
            definedVariables.put(var.getName(), var);
            return true;
        }
        return false;
    }

    private <T extends Var> T createVar(T t) {
        if (add(t)) return t;
        else throw new NamespaceException(t.getName());
    }

    public BoolVar createBool(String name) {
        return createVar(new BoolVar(name));
    }

    public IntVar createInt(String name) {
        return createVar(new IntVar(name));
    }

    public StringVar createString(String name) {
        return createVar(new StringVar(name));
    }

    public <E> SetVar<E> createSet(String name, Class<E> c) {
        return createVar(new SetVar<>(name));
    }

    public <E> ArrayVar<E> createArray(String name, Class<E> c) {
        return createVar(new ArrayVar<>(name));
    }

    public <E> Array2DVar<E> createArray2D(String name, Class<E> c) {
        return createVar(new Array2DVar<>(name));
    }

    public SetVar<Object> createSet(String name) {
        return createSet(name, Object.class);
    }

    public ArrayVar<Object> createArray(String name) {
        return createArray(name, Object.class);
    }

    public Array2DVar<Object> createArray2D(String name) {
        return createArray2D(name, Object.class);
    }

    public ReturningFunctionVar createFunction(String name) {
        return createVar(new ReturningFunctionVar(name));
    }

    public Var get(String name) {
        return definedVariables.get(name);
    }

    public Data toData() {
        DataBuilder builder = new DataBuilder();
        definedVariables.forEach((k,v) -> builder.put(k, v.getValue()));
        return builder.build();
    }
}
