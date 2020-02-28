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

    void testNegative() {
        String str = "To nie jest '" + '\\xa0' + "Ident'"
        stack.push(str)

        try {
            println Ident.from(stack);
            fail();
        } catch (IOException ex) {
            println(ex)
        }
    }

    void testPositive() {
        stack.push("something_OK")
        try {
            println Ident.from(stack)
        } catch (IOException ex) {
            fail()
        }
        stack.clear()
        String str = "'some \$% OK'"
        for (char c : str.toCharArray()) stack.push(c)

        try {
            println Ident.from(stack)
        } catch (IOException ex) {
            ex.printStackTrace()
            fail()
        }
    }
}
