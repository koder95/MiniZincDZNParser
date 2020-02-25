package pl.koder95.dznp.util;

public final class Strings {

    private Strings() {}

    public static String requireNonEmpty(String str) {
        if (str.isEmpty()) throw new EmptyStringException();
        else return str;
    }

    public static String requireNonEmpty(String str, String msg) {
        if (str.isEmpty()) throw new EmptyStringException(msg);
        else return str;
    }
}
