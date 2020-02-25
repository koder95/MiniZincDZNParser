package pl.koder95.dznp.util;

import java.util.Stack;

public final class Stacks {

	private Stacks() {}
	
	public static void returnToStack(Stack<Object> stack, Object...objects) {
		for (Object o : objects) stack.add(o);
	}
	
	public static void returnToStackAndThrow(Stack<Object> stack, Exception ex, Object...objects) throws Exception {
		returnToStack(stack, objects);
		throw ex;
	}
	
	@SuppressWarnings("unchecked")
	public static <E> E getType(Stack<Object> stack, Class<?> c) {
		Object obj = stack.pop();
		if (c.isInstance(obj)) return (E) obj;
		stack.add(obj);
		return null;
	}

    public static <E, S extends Stack<E>> S requireNonEmpty(S stack) {
		return stack == null || stack.empty()? null : stack;
    }
}
