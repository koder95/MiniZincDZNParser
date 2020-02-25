package pl.koder95.dznp.util;

public final class Strings {

    private Strings() {}
    
    public static String requireNonEmpty(String str) {
        return str.isEmpty()? null : str;
    }
}
