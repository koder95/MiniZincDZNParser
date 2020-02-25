package pl.koder95.dznp.lang;

public class NamespaceException extends RuntimeException {

    public NamespaceException(String name) {
        super(String.format("Namespace contains name: \"%s\"", name));
    }
}
