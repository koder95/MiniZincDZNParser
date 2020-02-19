package pl.koder95.dznp.lang.grammar

class IdentTest extends GroovyTestCase {

    private Stack<Object> stack;

    void setUp() {
        stack = new Stack<>();
    }

    void tearDown() {
        stack.clear();
        stack = null;
    }

    void testFrom() {
        testNegative();
        testPositive();
    }

    void testNegative() {
        stack.push("Coś");
        stack.push("jest");
        stack.push("nie");
        stack.push("tak");
        stack.push("!");
        try {
            println Ident.from(stack);
            fail();
        } catch (IOException ex) {
            println(ex)
        }
        stack.clear()
        stack.push("something_OK")
        try {
            println Ident.from(stack);
            fail();
        } catch (IOException ex) {
            println(ex)
        }
    }

    void testPositive() {

    }
}
