package pl.koder95.dznp;

import java.io.IOException;

/**
 * Signals problems during parsing input data.
 *
 * @author Kamil Mularski [Koder95]
 * @version 1.0.0
 */
public class ParserException extends IOException {

    /**
     * Constructs an {@code ParserException} with default error detail message.
     */
    public ParserException() {
        super("Cannot parse data!");
    }

    /**
     * Constructs an {@code ParserException} with the specified detail message.
     *
     * @param message
     *        The detail message (which is saved for later retrieval
     *        by the {@link #getMessage()} method)
     */
    public ParserException(String message) {
        super(message);
    }

    /**
     * Constructs an {@code ParserException} with the specified detail message
     * and cause.
     *
     * <p> Note that the detail message associated with {@code cause} is
     * <i>not</i> automatically incorporated into this exception's detail
     * message.
     *
     * @param message
     *        The detail message (which is saved for later retrieval
     *        by the {@link #getMessage()} method)
     *
     * @param cause
     *        The cause (which is saved for later retrieval by the
     *        {@link #getCause()} method).  (A null value is permitted,
     *        and indicates that the cause is nonexistent or unknown.)
     */
    public ParserException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an {@code ParserException} with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of {@code cause}).
     * This constructor is useful for parser exceptions that are little more
     * than wrappers for other throwables.
     *
     * @param cause
     *        The cause (which is saved for later retrieval by the
     *        {@link #getCause()} method).  (A null value is permitted,
     *        and indicates that the cause is nonexistent or unknown.)
     */
    public ParserException(Throwable cause) {
        super(cause);
    }
}
