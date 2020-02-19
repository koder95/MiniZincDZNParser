package pl.koder95.dznp.util

import java.time.LocalDate

class StacksTest extends GroovyTestCase {
    private Stack<Object> stack

    void setUp() {
        super.setUp()
        stack = new Stack<>()
        stack.push("")
        stack.push(Integer.valueOf(12))
        stack.push(Float.valueOf(523.543))
        stack.push(LocalDate.now())
    }

    void tearDown() {
        stack.clear()
        stack = null
    }

    void testPositiveGetType() {
        assertNotNull(Stacks.getType(stack, LocalDate.class))
        assertNotNull(Stacks.getType(stack, Float.class))
        assertNotNull(Stacks.getType(stack, Integer.class))
        assertNotNull(Stacks.getType(stack, String.class))
    }

    void testNegativeGetType() {
        assertNull(Stacks.getType(stack, Float.class))
        assertNull(Stacks.getType(stack, Integer.class))
        assertNull(Stacks.getType(stack, String.class))
        assertNotNull(Stacks.getType(stack, LocalDate.class))
    }
}
