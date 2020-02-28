package pl.koder95.dznp.lang;

import pl.koder95.dznp.util.Strings;

import java.util.Objects;

public class NamedObject {

    private final String name;

    public NamedObject(String name) {
        this.name = Objects.requireNonNull(Strings.requireNonEmpty(name));
    }

    public final String getName() {
        return name;
    }
}
