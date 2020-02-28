package pl.koder95.dznp.lang;

import pl.koder95.dznp.core.Data;

import java.util.Iterator;
import java.util.regex.Pattern;

public class Parameters extends Namespace implements Iterable<Var> {

    public static final String STARTS_WITH = "param";
    public static final Pattern PARAM_PATTERN = Pattern.compile(STARTS_WITH + "[0-9]+");

    @Override
    public BoolVar createBool(String name) {
        return PARAM_PATTERN.matcher(name).matches()? createBool() : null;
    }

    @Override
    public IntVar createInt(String name) {
        return PARAM_PATTERN.matcher(name).matches()? createInt() : null;
    }

    @Override
    public StringVar createString(String name) {
        return PARAM_PATTERN.matcher(name).matches()? createString() : null;
    }

    @Override
    public <E> SetVar<E> createSet(String name, Class<E> c) {
        return PARAM_PATTERN.matcher(name).matches()? createSet(c) : null;
    }

    @Override
    public <E> ArrayVar<E> createArray(String name, Class<E> c) {
        return PARAM_PATTERN.matcher(name).matches()? createArray(c) : null;
    }

    @Override
    public <E> Array2DVar<E> createArray2D(String name, Class<E> c) {
        return PARAM_PATTERN.matcher(name).matches()? createArray2D(c) : null;
    }

    @Override
    public SetVar<Object> createSet(String name) {
        return PARAM_PATTERN.matcher(name).matches()? createSet() : null;
    }

    @Override
    public ArrayVar<Object> createArray(String name) {
        return PARAM_PATTERN.matcher(name).matches()? createArray() : null;
    }

    @Override
    public Array2DVar<Object> createArray2D(String name) {
        return PARAM_PATTERN.matcher(name).matches()? createArray2D() : null;
    }

    @Override
    public ReturningFunctionVar createReturningFunction(String name) {
        return PARAM_PATTERN.matcher(name).matches()? createReturningFunction() : null;
    }

    @Override
    public Var get(String name) {
        return PARAM_PATTERN.matcher(name).matches()? super.get(name) : null;
    }

    @Override
    public Data toData() {
        return super.toData();
    }

    private String buildName(int index) {
        return STARTS_WITH + index;
    }

    private String buildNewName() {
        return buildName(lastCreatedIndex++);
    }

    public Var get(int index) {
        return get(buildName(index));
    }

    private int lastCreatedIndex = -1;

    public int size() {
        return lastCreatedIndex+1;
    }

    public BoolVar createBool() {
        return super.createBool(buildNewName());
    }

    public IntVar createInt() {
        return super.createInt(buildNewName());
    }

    public StringVar createString() {
        return super.createString(buildNewName());
    }

    public <E> SetVar<E> createSet(Class<E> c) {
        return super.createSet(buildNewName(), c);
    }

    public <E> ArrayVar<E> createArray(Class<E> c) {
        return super.createArray(buildNewName(), c);
    }

    public <E> Array2DVar<E> createArray2D(Class<E> c) {
        return super.createArray2D(buildNewName(), c);
    }

    public SetVar<Object> createSet() {
        return super.createSet(buildNewName());
    }

    public ArrayVar<Object> createArray() {
        return super.createArray(buildNewName());
    }

    public Array2DVar<Object> createArray2D() {
        return super.createArray2D(buildNewName());
    }

    public ReturningFunctionVar createReturningFunction() {
        return super.createReturningFunction(buildNewName());
    }

    @Override
    public Iterator<Var> iterator() {
        return new Iterator<Var>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < size();
            }

            @Override
            public Var next() {
                return get(i++);
            }
        };
    }
}
