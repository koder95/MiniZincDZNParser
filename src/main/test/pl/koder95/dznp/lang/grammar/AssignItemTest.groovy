package pl.koder95.dznp.lang.grammar

class AssignItemTest extends GroovyTestCase {
    Stack<Object> stack
    void setUp() {
        super.setUp()
        stack = new Stack<>()
    }

    void tearDown() {
        stack.clear()
        stack = null
    }

    void testFrom() {
        stack.push(new Ident("key"))
        stack.push("=")
        stack.push(new Expr())
        AssignItem ai = AssignItem.from(stack)
        println ai
        assertNotNull(ai)
    }
}
