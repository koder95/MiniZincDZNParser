package pl.koder95.dznp.util;

import java.util.Stack;

/**
 * Singleton class contains static methods helpful to stacks managing.
 */
public final class Stacks {

	private Stacks() {}

	/**
	 * Adds to stack objects in correct order.
	 * @param stack stack of objects
	 * @param objects objects in correct order
	 */
	public static void returnToStack(Stack<Object> stack, Object...objects) {
		for (Object o : objects) stack.add(o);
	}

	/**
	 * Adds to stack objects in correct order and throw exception.
	 *
	 * @param stack stack of objects
	 * @param ex exception to throw
	 * @param objects objects in correct order
   * @param <E> type of exception
	 * @throws E param {@code ex}
	 */
	public static <E extends Exception> void returnToStackAndThrow(Stack<Object> stack, E ex, Object...objects) throws E {
		returnToStack(stack, objects);
		throw ex;
	}

	/**
	 * Returns top of stack if is instance of specified type.
	 * If top object is in other type then returns back to stack and
	 * this method returns {@code null}.
	 *
	 * @param stack stack of objects
	 * @param c type to return
	 * @param <E> type returning specified by {@code c} parameter
	 * @return top of stack in specified type or {@code null} if top is other type
	 */
	@SuppressWarnings(value = "unchecked")
	public static <E> E getType(Stack<Object> stack, Class<?> c) {
		E result = null;
		Object obj = stack.pop();
		if (c.isInstance(obj)) {
			result = (E) obj;
		} else {
			stack.add(obj);
		}
		return result;
	}

	/**
	 * Checks specified stack if it is {@code null} or empty
	 * and if true then returns {@code null}.
	 *
	 * @param stack any stack
	 * @param <E> type of elements in stack
	 * @param <S> type of stack to return
	 * @return
	 * 		{@code stack} - if it is not empty,
	 * 		{@code null} - otherwise
	 */
    public static <E, S extends Stack<E>> S requireNonEmpty(S stack) {
		return stack == null || stack.empty()? null : stack;
    }
}
