package pl.koder95.dznp.lang.grammar

class ModelTest extends GroovyTestCase {
    Stack<Object> stack
    void setUp() {
        super.setUp()
        stack = new Stack<>();
    }

    void tearDown() {
        stack.clear()
        stack = null;
    }

    void testFrom() {
        stack.push(new AssignItem(new Ident("key"), new Expr<Object>()))
        stack.push(";")

        Model model = Model.from(stack)
        println model
        println model.assignItemList
        assertEquals(false, model.assignItemList.isEmpty())

        stack.push(";")
        stack.push(new AssignItem(new Ident("key"), new Expr<Object>()))

        model = Model.from(stack)
        println model
        println model.assignItemList
        assertEquals(false, model.assignItemList.isEmpty())
    }
}
