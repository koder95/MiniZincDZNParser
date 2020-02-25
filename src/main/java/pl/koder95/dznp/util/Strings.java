package pl.koder95.dznp.util;

public class Strings {

    public static String requireNonEmpty(String str) {
        return str.isEmpty()? null : str;
    }
}
