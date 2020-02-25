package pl.koder95.dznp;

import java.io.IOException;

public class ParserException extends IOException {
    public ParserException() {
        super("Cannot parse data!");
    }

    public ParserException(String message) {
        super(message);
    }

    public ParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParserException(Throwable cause) {
        super(cause);
    }
}
