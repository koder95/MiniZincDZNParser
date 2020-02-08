package pl.koder95.dznp.util;

public class EmptyStringException extends RuntimeException {

    public EmptyStringException(String msg) {
        super(msg);
    }

    public EmptyStringException() {
        this("Required non empty string!");
    }
}
