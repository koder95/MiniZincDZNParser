package pl.koder95.dznp.ast;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;
import java.util.stream.Stream;

/**
 * Reprezentuje różne typy literałów w języku MiniZinc.
 * <p>
 * Enum zawiera wzorce wyrażeń regularnych dla wszystkich typów literałów
 * zdefiniowanych w gramatyce MiniZinc, w tym liczb całkowitych, zmiennoprzecinkowych,
 * wartości logicznych, identyfikatorów i stringów.
 * </p>
 *
 * <h2>Przykład użycia:</h2>
 * <pre>{@code
 * // Znalezienie wszystkich pasujących wzorców
 * List<LiteralPattern> patterns = LiteralPattern.findAllMatching("123");
 * for (LiteralPattern pattern : patterns) {
 *     System.out.println(pattern.getDescription());
 * }
 *
 * // Sprawdzenie czy string pasuje do wzorca
 * if (LiteralPattern.IDENTIFIER.matches("myVariable")) {
 *     System.out.println("Poprawny identyfikator");
 * }
 *
 * // Iteracja po wszystkich literałach danej kategorii
 * for (LiteralPattern lit : LiteralPattern.values()) {
 *     if (lit.getCategory() == Category.INTEGER) {
 *         System.out.println(lit.getDescription());
 *     }
 * }
 * }</pre>
 *
 * @author Kamil Jan Mularski [@Koder95]
 * @version 2.0
 * @see <a href="https://www.minizinc.org/">MiniZinc Official Website</a>
 */
public enum LiteralPattern {

    // ==================== LITERAŁY LICZBOWE CAŁKOWITE ====================

    /**
     * Literał liczby całkowitej w systemie dziesiętnym.
     * <p>
     * Reprezentuje sekwencję cyfr dziesiętnych bez prefiksu.
     * </p>
     *
     * <h3>Przykłady poprawnych wartości:</h3>
     * <ul>
     *   <li>{@code 0} - zero</li>
     *   <li>{@code 123} - liczba dodatnia</li>
     *   <li>{@code 9876543210} - duża liczba</li>
     * </ul>
     *
     * @see #HEX_INT
     * @see #OCTAL_INT
     */
    DECIMAL_INT(
            "\\d+",
            "Decimal integer",
            Category.INTEGER
    ),

    /**
     * Literał liczby całkowitej w systemie szesnastkowym.
     * <p>
     * Rozpoczyna się prefiksem {@code 0x} lub {@code 0X}, po którym następują
     * cyfry szesnastkowe (0-9, A-F, a-f).
     * </p>
     *
     * <h3>Przykłady poprawnych wartości:</h3>
     * <ul>
     *   <li>{@code 0x0} - zero w hex</li>
     *   <li>{@code 0xFF} - 255 w dziesiętnym</li>
     *   <li>{@code 0x1A2B3C} - wielka liczba hex</li>
     *   <li>{@code 0XaBcDeF} - małe i wielkie litery</li>
     * </ul>
     *
     * @see #DECIMAL_INT
     * @see #OCTAL_INT
     */
    HEX_INT(
            "0[xX][0-9A-Fa-f]+",
            "Hexadecimal integer",
            Category.INTEGER
    ),

    /**
     * Literał liczby całkowitej w systemie ósemkowym.
     * <p>
     * Rozpoczyna się prefiksem {@code 0o} lub {@code 0O}, po którym następują
     * cyfry ósemkowe (0-7).
     * </p>
     *
     * <h3>Przykłady poprawnych wartości:</h3>
     * <ul>
     *   <li>{@code 0o0} - zero w octal</li>
     *   <li>{@code 0o755} - 493 w dziesiętnym (uprawnienia Unix)</li>
     *   <li>{@code 0O777} - 511 w dziesiętnym</li>
     * </ul>
     *
     * @see #DECIMAL_INT
     * @see #HEX_INT
     */
    OCTAL_INT(
            "0[oO][0-7]+",
            "Octal integer",
            Category.INTEGER
    ),

    // ==================== LITERAŁY ZMIENNOPRZECINKOWE ====================

    /**
     * Literał liczby zmiennoprzecinkowej w notacji dziesiętnej.
     * <p>
     * Składa się z części całkowitej, kropki dziesiętnej i części ułamkowej.
     * Nie zawiera wykładnika.
     * </p>
     *
     * <h3>Przykłady poprawnych wartości:</h3>
     * <ul>
     *   <li>{@code 0.0} - zero</li>
     *   <li>{@code 3.14} - pi przybliżone</li>
     *   <li>{@code 123.456} - liczba z częścią dziesiętną</li>
     * </ul>
     *
     * @see #FLOAT_DECIMAL_EXP
     * @see #FLOAT_EXP_ONLY
     */
    FLOAT_DECIMAL(
            "\\d+\\.\\d+",
            "Decimal float",
            Category.FLOAT
    ),

    /**
     * Literał liczby zmiennoprzecinkowej w notacji wykładniczej z częścią dziesiętną.
     * <p>
     * Zawiera część całkowitą, kropkę, część ułamkową oraz wykładnik oznaczony
     * literą {@code E} lub {@code e}.
     * </p>
     *
     * <h3>Przykłady poprawnych wartości:</h3>
     * <ul>
     *   <li>{@code 1.5e10} - 1.5 × 10^10</li>
     *   <li>{@code 2.0E-5} - 2.0 × 10^-5</li>
     *   <li>{@code 3.14159e+2} - 314.159</li>
     * </ul>
     *
     * @see #FLOAT_DECIMAL
     * @see #FLOAT_EXP_ONLY
     */
    FLOAT_DECIMAL_EXP(
            "\\d+\\.\\d+[Ee][+-]?\\d+",
            "Decimal float with exponent",
            Category.FLOAT
    ),

    /**
     * Literał liczby zmiennoprzecinkowej w notacji wykładniczej bez części dziesiętnej.
     * <p>
     * Zawiera tylko część całkowitą i wykładnik oznaczony literą {@code E} lub {@code e}.
     * Brak kropki dziesiętnej.
     * </p>
     *
     * <h3>Przykłady poprawnych wartości:</h3>
     * <ul>
     *   <li>{@code 1e10} - 1 × 10^10</li>
     *   <li>{@code 5E-3} - 0.005</li>
     *   <li>{@code 123e+5} - 12300000</li>
     * </ul>
     *
     * @see #FLOAT_DECIMAL
     * @see #FLOAT_DECIMAL_EXP
     */
    FLOAT_EXP_ONLY(
            "\\d+[Ee][+-]?\\d+",
            "Float with exponent only",
            Category.FLOAT
    ),

    /**
     * Literał liczby zmiennoprzecinkowej w formacie szesnastkowym z częścią ułamkową.
     * <p>
     * Rozpoczyna się od {@code 0x} lub {@code 0X}, zawiera kropkę dziesiętną
     * w notacji hex i wykładnik binarny oznaczony {@code p} lub {@code P}.
     * </p>
     *
     * <h3>Przykłady poprawnych wartości:</h3>
     * <ul>
     *   <li>{@code 0x1.8p3} - 1.5 × 2^3 = 12.0</li>
     *   <li>{@code 0XA.Fp-2} - (10 + 15/16) × 2^-2</li>
     *   <li>{@code 0x1A.BC0p+10} - hex float z dużym wykładnikiem</li>
     * </ul>
     *
     * @see #FLOAT_HEX_INT
     */
    FLOAT_HEX_FRACTIONAL(
            "0[xX]([0-9a-fA-F]*\\.[0-9a-fA-F]+|[0-9a-fA-F]+\\.)([pP][+-]?\\d+)",
            "Hexadecimal float with fraction",
            Category.FLOAT
    ),

    /**
     * Literał liczby zmiennoprzecinkowej w formacie szesnastkowym bez części ułamkowej.
     * <p>
     * Rozpoczyna się od {@code 0x} lub {@code 0X}, zawiera tylko część całkowitą
     * w notacji hex i wykładnik binarny oznaczony {@code p} lub {@code P}.
     * </p>
     *
     * <h3>Przykłady poprawnych wartości:</h3>
     * <ul>
     *   <li>{@code 0x1p10} - 1 × 2^10 = 1024.0</li>
     *   <li>{@code 0XFFp-4} - 255 × 2^-4 = 15.9375</li>
     *   <li>{@code 0xABCp+5} - hex float</li>
     * </ul>
     *
     * @see #FLOAT_HEX_FRACTIONAL
     */
    FLOAT_HEX_INT(
            "0[xX][0-9a-fA-F]+[pP][+-]?\\d+",
            "Hexadecimal float integer part",
            Category.FLOAT
    ),

    // ==================== LITERAŁY BOOLOWSKIE ====================

    /**
     * Literał wartości logicznej fałsz.
     * <p>
     * Reprezentuje wartość logiczną {@code false} w MiniZinc.
     * </p>
     *
     * <h3>Przykład:</h3>
     * <pre>{@code
     * constraint x = false;
     * }</pre>
     *
     * @see #BOOL_TRUE
     */
    BOOL_FALSE(
            "false",
            "Boolean false",
            Category.BOOLEAN
    ),

    /**
     * Literał wartości logicznej prawda.
     * <p>
     * Reprezentuje wartość logiczną {@code true} w MiniZinc.
     * </p>
     *
     * <h3>Przykład:</h3>
     * <pre>{@code
     * constraint x = true;
     * }</pre>
     *
     * @see #BOOL_FALSE
     */
    BOOL_TRUE(
            "true",
            "Boolean true",
            Category.BOOLEAN
    ),

    // ==================== IDENTYFIKATORY ====================

    /**
     * Standardowy identyfikator zmiennej, funkcji lub typu.
     * <p>
     * Rozpoczyna się opcjonalnie od podkreślenia {@code _}, następnie literą
     * (a-z, A-Z), po czym mogą występować litery, cyfry i podkreślenia.
     * </p>
     *
     * <h3>Przykłady poprawnych wartości:</h3>
     * <ul>
     *   <li>{@code x} - jednoliterowy identyfikator</li>
     *   <li>{@code myVariable} - camelCase</li>
     *   <li>{@code my_variable_123} - snake_case z cyframi</li>
     *   <li>{@code _privateVar} - zaczyna się od podkreślenia</li>
     *   <li>{@code MAX_VALUE} - uppercase</li>
     * </ul>
     *
     * <h3>Przykłady niepoprawnych wartości:</h3>
     * <ul>
     *   <li>{@code 123var} - nie może zaczynać się od cyfry</li>
     *   <li>{@code my-var} - nie może zawierać myślnika</li>
     * </ul>
     *
     * @see #QUOTED_IDENTIFIER
     */
    IDENTIFIER(
            "_?[A-Za-z][A-Za-z0-9_]*",
            "Standard identifier",
            Category.IDENTIFIER
    ),

    /**
     * Identyfikator w apostrofach (quoted identifier).
     * <p>
     * Pozwala na używanie jako identyfikatorów dowolnych znaków (z wyjątkiem
     * apostrofu, nowej linii i znaku null) poprzez umieszczenie ich w apostrofach.
     * Przydatne do używania słów kluczowych jako identyfikatorów lub operatorów.
     * </p>
     *
     * <h3>Przykłady poprawnych wartości:</h3>
     * <ul>
     *   <li>{@code 'for'} - słowo kluczowe jako identyfikator</li>
     *   <li>{@code 'my-variable'} - identyfikator z myślnikiem</li>
     *   <li>{@code 'x + y'} - operator jako identyfikator</li>
     *   <li>{@code '123'} - cyfry jako identyfikator</li>
     * </ul>
     *
     * @see #IDENTIFIER
     */
    QUOTED_IDENTIFIER(
            "'[^'\\x0a\\x0d\\x00]+'",
            "Quoted identifier",
            Category.IDENTIFIER
    ),

    // ==================== ZMIENNE TYPÓW ====================

    /**
     * Zmienna typu (type-inst variable).
     * <p>
     * Rozpoczyna się od znaku {@code $}, po którym następuje litera lub {@code $},
     * a następnie litery, cyfry i podkreślenia. Używana w definicjach parametrycznych
     * typów i funkcji.
     * </p>
     *
     * <h3>Przykłady poprawnych wartości:</h3>
     * <ul>
     *   <li>{@code $T} - prosta zmienna typu</li>
     *   <li>{@code $ElementType} - opisowa zmienna typu</li>
     *   <li>{@code $T1} - zmienna typu z cyfrą</li>
     *   <li>{@code $$} - podwójny dolar</li>
     * </ul>
     *
     * <h3>Przykład użycia:</h3>
     * <pre>{@code
     * function var $T: maximum(array[$U] of var $T: x);
     * }</pre>
     */
    TYPE_VARIABLE(
            "\\$[A-Za-z$][A-Za-z0-9_]*",
            "Type-inst variable",
            Category.TYPE_VARIABLE
    ),

    // ==================== LITERAŁY STRINGOWE ====================

    /**
     * Zawartość literału stringowego.
     * <p>
     * Reprezentuje znaki wewnątrz cudzysłowów, włącznie z sekwencjami escape:
     * </p>
     * <ul>
     *   <li>{@code \n} - nowa linia</li>
     *   <li>{@code \t} - tabulator</li>
     *   <li>{@code \"} - cudzysłów</li>
     *   <li>{@code \\} - backslash</li>
     *   <li>{@code \xxx} - znak w notacji ósemkowej (1-3 cyfry)</li>
     *   <li>{@code \xHH} - znak w notacji hex (1-2 cyfry)</li>
     * </ul>
     *
     * <h3>Przykłady poprawnych wartości:</h3>
     * <ul>
     *   <li>{@code Hello World} - prosty tekst</li>
     *   <li>{@code Line 1\nLine 2} - z nową linią</li>
     *   <li>{@code He said \"Hi\"} - z cudzysłowami</li>
     *   <li>{@code Path: C:\\Users\\name} - z backslashami</li>
     *   <li>{@code \x41\x42\x43} - ABC w hex</li>
     * </ul>
     */
    STRING_CONTENTS(
            "([^\"\\n\\\\]|\\\\[0-7][0-7]?[0-7]?|\\\\x[0-9a-fA-F][0-9a-fA-F]?|\\\\n|\\\\t|\\\\\"|\\\\\\\\)*",
            "String contents",
            Category.STRING
    ),

    // ==================== SPECJALNE ====================

    /**
     * Podkreślenie (wildcard/underscore).
     * <p>
     * Używane jako symbol wieloznaczny (wildcard) w dopasowaniu wzorców,
     * destrukturyzacji lub w miejscach, gdzie wartość jest ignorowana.
     * </p>
     *
     * <h3>Przykłady użycia:</h3>
     * <pre>{@code
     * % Ignorowanie wartości w generatorze
     * constraint forall(i in 1..10, _ in 1..5)(x[i] > 0);
     *
     * % Wildcard w enumerate
     * enum Color = {_, RED, GREEN, BLUE};
     * }</pre>
     */
    UNDERSCORE(
            "_",
            "Underscore/wildcard",
            Category.SPECIAL
    );

    // ==================== POLA ====================

    /**
     * Skompilowany wzorzec wyrażenia regularnego dla tego literału.
     * <p>
     * Wzorzec jest skompilowany z dodanymi anchorami {@code ^} i {@code $}
     * aby dopasowywać cały string.
     * </p>
     */
    private final Pattern compiledPattern;

    /**
     * Surowy wzorzec wyrażenia regularnego jako string.
     */
    private final String patternString;

    /**
     * Czytelny opis literału po angielsku.
     */
    private final String description;

    /**
     * Kategoria, do której należy ten literał.
     *
     * @see Category
     */
    private final Category category;

    // ==================== KONSTRUKTOR ====================

    /**
     * Konstruktor enuma {@code LiteralPattern}.
     * <p>
     * Tworzy nową wartość literału z podanym wzorcem, opisem i kategorią.
     * Wzorzec jest automatycznie kompilowany do {@link Pattern} z anchorami.
     * </p>
     *
     * @param pattern wzorzec wyrażenia regularnego (bez anchorów)
     * @param description czytelny opis literału
     * @param category kategoria literału
     * @throws java.util.regex.PatternSyntaxException jeśli wzorzec jest niepoprawny
     */
    LiteralPattern(String pattern, String description, Category category) {
        this.patternString = pattern;
        this.compiledPattern = Pattern.compile("^" + pattern + "$");
        this.description = description;
        this.category = category;
    }

    // ==================== GETTERY ====================

    /**
     * Zwraca surowy wzorzec wyrażenia regularnego.
     * <p>
     * Uwaga: zwracany wzorzec nie zawiera anchorów {@code ^} i {@code $}.
     * </p>
     *
     * @return wzorzec regex jako string
     */
    public String getPattern() {
        return patternString;
    }

    /**
     * Zwraca czytelny opis literału.
     *
     * @return opis literału po angielsku
     */
    public String getDescription() {
        return description;
    }

    /**
     * Zwraca kategorię literału.
     *
     * @return kategoria typu {@link Category}
     */
    public Category getCategory() {
        return category;
    }

    // ==================== METODY DOPASOWANIA ====================

    /**
     * Sprawdza, czy podany string dokładnie pasuje do wzorca tego literału.
     * <p>
     * Metoda dopasowuje cały string od początku do końca.
     * </p>
     *
     * @param input string do sprawdzenia
     * @return {@code true} jeśli input pasuje do wzorca, {@code false} w przeciwnym razie
     * @throws NullPointerException jeśli input jest null
     */
    public boolean matches(String input) {
        return compiledPattern.matcher(input).matches();
    }

    /**
     * Tworzy {@link Matcher} dla podanego stringu.
     * <p>
     * Pozwala na bardziej zaawansowane operacje na wzorcu, takie jak
     * znajdowanie grup, częściowe dopasowanie, itp.
     * </p>
     *
     * @param input string do przetworzenia
     * @return matcher dla tego wzorca i podanego stringu
     * @throws NullPointerException jeśli input jest null
     */
    public Matcher matcher(String input) {
        return compiledPattern.matcher(input);
    }

    // ==================== METODY STATYCZNE ====================

    private static Stream<LiteralPattern> match(String input) {
        return Arrays.stream(values())
                .filter(lp -> lp.matches(input));
    }

    /**
     * Znajduje wszystkie wzorce literałów pasujące do podanego stringu.
     * <p>
     * Ponieważ niektóre wzorce mogą się nakładać (np. {@code "123"} pasuje zarówno
     * do {@link #DECIMAL_INT} jak i może być częścią {@link #IDENTIFIER} jako {@code "_123"}),
     * metoda zwraca listę wszystkich pasujących wzorców w kolejności definicji enuma.
     * </p>
     *
     * <h3>Przykład użycia:</h3>
     * <pre>{@code
     * List<LiteralPattern> patterns = LiteralPattern.findAllMatching("true");
     * // Może zwrócić: [BOOL_TRUE, IDENTIFIER] jeśli "true" pasuje do obu
     *
     * for (LiteralPattern pattern : patterns) {
     *     System.out.println(pattern.getDescription());
     * }
     * }</pre>
     *
     * <h3>Obsługa niejednoznaczności:</h3>
     * <p>
     * W przypadku gdy string pasuje do wielu wzorców, należy zastosować
     * priorytetyzację na podstawie kontekstu lub kolejności wzorców.
     * Zazwyczaj bardziej specyficzne wzorce (jak {@link #BOOL_TRUE})
     * powinny mieć wyższy priorytet niż ogólne (jak {@link #IDENTIFIER}).
     * </p>
     *
     * @param input string do przeanalizowania
     * @return niemodyfikowalna lista wszystkich pasujących wzorców;
     *         pusta lista, jeśli żaden wzorzec nie pasuje
     * @throws NullPointerException jeśli input jest null
     *
     * @see #matches(String)
     * @see #findFirstMatching(String)
     */
    public static List<LiteralPattern> findAllMatching(String input) {
        return match(input).toList();
    }

    /**
     * Znajduje pierwszy pasujący wzorzec literału dla podanego stringu.
     * <p>
     * Przeszukuje wszystkie wartości enuma w kolejności definicji i zwraca
     * pierwszy pasujący literał. Jeśli żaden nie pasuje, zwraca {@code null}.
     * </p>
     *
     * <h3>Uwaga o kolejności:</h3>
     * <p>
     * Ponieważ niektóre wzorce mogą się nakładać, kolejność sprawdzania
     * ma znaczenie. Bardziej szczegółowe wzorce są sprawdzane pierwsze.
     * Jeśli potrzebujesz wszystkich pasujących wzorców, użyj
     * {@link #findAllMatching(String)}.
     * </p>
     *
     * <h3>Przykład użycia:</h3>
     * <pre>{@code
     * LiteralPattern pattern = LiteralPattern.findFirstMatching("123");
     * if (pattern != null) {
     *     System.out.println("Znaleziono: " + pattern.getDescription());
     * }
     * }</pre>
     *
     * @param input string do zidentyfikowania
     * @return pierwszy pasujący {@code LiteralPattern} lub {@code null} jeśli żaden nie pasuje
     * @throws NullPointerException jeśli input jest null
     *
     * @see #matches(String)
     * @see #findAllMatching(String)
     */
    public static LiteralPattern findFirstMatching(String input) {
        for (LiteralPattern literal : values()) {
            if (literal.matches(input)) {
                return literal;
            }
        }
        return null;
    }

    /**
     * Znajduje wszystkie wzorce literałów należące do określonej kategorii,
     * które pasują do podanego stringu.
     * <p>
     * Przydatne, gdy chcemy ograniczyć przeszukiwanie tylko do wzorców
     * określonego typu (np. tylko liczby całkowite).
     * </p>
     *
     * <h3>Przykład użycia:</h3>
     * <pre>{@code
     * // Znajdź tylko wzorce numeryczne pasujące do "123"
     * List<LiteralPattern> numericPatterns =
     *     LiteralPattern.findAllMatchingByCategory("123", Category.INTEGER);
     * }</pre>
     *
     * @param input string do przeanalizowania
     * @param category kategoria wzorców do sprawdzenia
     * @return niemodyfikowalna lista pasujących wzorców z danej kategorii;
     *         pusta lista, jeśli żaden wzorzec nie pasuje
     * @throws NullPointerException jeśli input lub category jest null
     *
     * @see #findAllMatching(String)
     * @see Category
     */
    public static List<LiteralPattern> findAllMatchingByCategory(String input, Category category) {
        return match(input).filter(lp -> lp.category == category).toList();
    }

    // ==================== METODY OBJECT ====================

    /**
     * Zwraca stringową reprezentację literału.
     * <p>
     * Format: {@code NAZWA [KATEGORIA]: opis}
     * </p>
     *
     * @return sformatowany string z informacjami o literale
     */
    @Override
    public String toString() {
        return String.format("%s [%s]: %s", name(), category, description);
    }

    // ==================== ENUM WEWNĘTRZNY ====================

    /**
     * Kategorie literałów w MiniZinc.
     * <p>
     * Grupuje literały według ich semantycznego typu, co ułatwia
     * przetwarzanie i kategoryzację w parserze lub kompilatorze.
     * </p>
     *
     * <h3>Kategorie:</h3>
     * <ul>
     *   <li>{@link #INTEGER} - liczby całkowite (decimal, hex, octal)</li>
     *   <li>{@link #FLOAT} - liczby zmiennoprzecinkowe (decimal, hex)</li>
     *   <li>{@link #BOOLEAN} - wartości logiczne (true, false)</li>
     *   <li>{@link #IDENTIFIER} - identyfikatory zmiennych i funkcji</li>
     *   <li>{@link #TYPE_VARIABLE} - zmienne typów ($T, $U, ...)</li>
     *   <li>{@link #STRING} - zawartość stringów</li>
     *   <li>{@link #SPECIAL} - symbole specjalne (wildcard)</li>
     * </ul>
     */
    public enum Category {
        /**
         * Literały liczb całkowitych.
         * <p>
         * Obejmuje liczby w systemach: dziesiętnym, szesnastkowym i ósemkowym.
         * </p>
         */
        INTEGER,

        /**
         * Literały liczb zmiennoprzecinkowych.
         * <p>
         * Obejmuje liczby w notacji dziesiętnej i szesnastkowej,
         * z wykładnikami i bez.
         * </p>
         */
        FLOAT,

        /**
         * Literały wartości logicznych.
         * <p>
         * Tylko dwie wartości: {@code true} i {@code false}.
         * </p>
         */
        BOOLEAN,

        /**
         * Identyfikatory.
         * <p>
         * Nazwy zmiennych, funkcji, typów itp. Obejmuje zarówno standardowe
         * identyfikatory, jak i quoted identifiers.
         * </p>
         */
        IDENTIFIER,

        /**
         * Zmienne typów.
         * <p>
         * Parametry typów używane w definicjach generycznych funkcji i predykatów.
         * Rozpoczynają się od znaku {@code $}.
         * </p>
         */
        TYPE_VARIABLE,

        /**
         * Zawartość stringów.
         * <p>
         * Znaki i sekwencje escape wewnątrz literałów stringowych.
         * </p>
         */
        STRING,

        /**
         * Symbole specjalne.
         * <p>
         * Znaki o specjalnym znaczeniu, takie jak wildcard ({@code _}).
         * </p>
         */
        SPECIAL
    }
}