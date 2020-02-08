package pl.koder95.dznp.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Stack;

public class StacksTest {

    private Stack<Object> stack;

    @Before
    public void setUp() throws Exception {
        stack = new Stack<>();
        stack.push("");
        stack.push(Integer.valueOf(12));
        stack.push(Float.valueOf(523.543f));
        stack.push(LocalDate.now());
    }

    @After
    public void tearDown() throws Exception {
        stack.clear();
        stack = null;
    }

    @Test
    public void testPositiveGetType() {
        Assert.assertNotNull(Stacks.getType(stack, LocalDate.class));
        Assert.assertNotNull(Stacks.getType(stack, Float.class));
        Assert.assertNotNull(Stacks.getType(stack, Integer.class));
        Assert.assertNotNull(Stacks.getType(stack, String.class));
    }

    @Test
    public void testNegativeGetType() {
        Assert.assertNull(Stacks.getType(stack, Float.class));
        Assert.assertNull(Stacks.getType(stack, Integer.class));
        Assert.assertNull(Stacks.getType(stack, String.class));
        Assert.assertNotNull(Stacks.getType(stack, LocalDate.class));
    }
}