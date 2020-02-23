package pl.koder95.dznp.util;

import java.util.Stack;

public final class Stacks {

	private Stacks() {}
	
	public static void returnToStack(Stack<Object> stack, Object...objects) {
		for (Object o : objects) stack.add(o);
	}
	
	public static <E extends Exception> void returnToStackAndThrow(Stack<Object> stack, E ex, Object...objects) throws E {
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
}
