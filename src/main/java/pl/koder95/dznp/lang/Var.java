package pl.koder95.dznp.lang;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Var<T> extends NamedObject {

    private final Type type;
    private T value;

    Var(Type type, String name, T value) {
        super(name);
        this.value = value;
        this.type = type;
    }

    public T getValue() {
        return value;
    }

    void setValue(T value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        ARRAY2D(Array2DVar.class), ARRAY(ArrayVar.class), BOOL(BoolVar.class), FLOAT(FloatVar.class),
        INT(IntVar.class), RETURNING_FUNCTION(ReturningFunctionVar.class), SET(SetVar.class), STRING(StringVar.class);

        private final Class<? extends Var> type;

        Type(Class<? extends Var> type) {
            this.type = type;
        }

        public <E extends Var> E create(Namespace ns, String name) throws NoSuchMethodException,
                InvocationTargetException, IllegalAccessException {
            String methodName = "create";
            String typeName = type.getSimpleName();
            methodName += typeName;
            if (methodName.endsWith("Var")) methodName = methodName.substring(0, methodName.lastIndexOf("Var"));
            Method method = ns.getClass().getDeclaredMethod(methodName, String.class);
            return (E) method.invoke(ns, name);
        }

        public <E extends Var> E createOrNull(Namespace ns, String name) {
            try {
                return create(ns, name);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                return null;
            }
        }
    }
}
