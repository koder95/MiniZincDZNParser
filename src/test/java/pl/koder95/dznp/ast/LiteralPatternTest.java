package pl.koder95.dznp.ast;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testy jednostkowe dla {@link LiteralPattern}.
 * <p>
 * Testuje wszystkie typy literałów, metody dopasowania, identyfikacji
 * oraz przypadki brzegowe. Uwzględnia fakt, że jeden string może pasować
 * do wielu wzorców.
 * </p>
 *
 * @author Kamil Jan Mularski [@Koder95]
 * @version 2.0
 */
@DisplayName("LiteralPattern Tests")
class LiteralPatternTest {

    // ==================== TESTY LICZB CAŁKOWITYCH ====================

    @Nested
    @DisplayName("Integer Literals Tests")
    class IntegerLiteralsTest {

        @Nested
        @DisplayName("Decimal Integer Tests")
        class DecimalIntegerTest {

            @ParameterizedTest
            @ValueSource(strings = {"0", "1", "123", "999", "1234567890", "42"})
            @DisplayName("Should match valid decimal integers")
            void shouldMatchValidDecimalIntegers(String input) {
                assertTrue(LiteralPattern.DECIMAL_INT.matches(input));
            }

            @ParameterizedTest
            @ValueSource(strings = {
                    "0x123",     // hex prefix
                    "0o777",     // octal prefix
                    "123.45",    // float
                    "1e10",      // exponent
                    "-123",      // negative sign
                    "+123",      // positive sign
                    "12_34",     // underscore
                    "abc",       // letters
                    ""           // empty
            })
            @DisplayName("Should not match invalid decimal integers")
            void shouldNotMatchInvalidDecimalIntegers(String input) {
                assertFalse(LiteralPattern.DECIMAL_INT.matches(input));
            }
        }

        @Nested
        @DisplayName("Hexadecimal Integer Tests")
        class HexadecimalIntegerTest {

            @ParameterizedTest
            @ValueSource(strings = {
                    "0x0", "0x1", "0xFF", "0xABCD", "0x123ABC",
                    "0X0", "0XFF", "0Xabcdef", "0x1A2B3C4D"
            })
            @DisplayName("Should match valid hexadecimal integers")
            void shouldMatchValidHexIntegers(String input) {
                assertTrue(LiteralPattern.HEX_INT.matches(input));
            }

            @ParameterizedTest
            @ValueSource(strings = {
                    "0x",        // no digits
                    "0xG",       // invalid hex digit
                    "0x 123",    // space
                    "1x123",     // wrong prefix
                    "0X",        // uppercase, no digits
                    "123",       // no prefix
                    "0o123"      // octal prefix
            })
            @DisplayName("Should not match invalid hexadecimal integers")
            void shouldNotMatchInvalidHexIntegers(String input) {
                assertFalse(LiteralPattern.HEX_INT.matches(input));
            }
        }

        @Nested
        @DisplayName("Octal Integer Tests")
        class OctalIntegerTest {

            @ParameterizedTest
            @ValueSource(strings = {
                    "0o0", "0o7", "0o755", "0o1234567",
                    "0O0", "0O777", "0O123"
            })
            @DisplayName("Should match valid octal integers")
            void shouldMatchValidOctalIntegers(String input) {
                assertTrue(LiteralPattern.OCTAL_INT.matches(input));
            }

            @ParameterizedTest
            @ValueSource(strings = {
                    "0o",        // no digits
                    "0o8",       // invalid octal digit
                    "0o9",       // invalid octal digit
                    "0o 123",    // space
                    "123",       // no prefix
                    "0x123"      // hex prefix
            })
            @DisplayName("Should not match invalid octal integers")
            void shouldNotMatchInvalidOctalIntegers(String input) {
                assertFalse(LiteralPattern.OCTAL_INT.matches(input));
            }
        }

        @Test
        @DisplayName("All integer literals should be in INTEGER category")
        void allIntegerLiteralsShouldBeInIntegerCategory() {
            assertEquals(LiteralPattern.Category.INTEGER,
                    LiteralPattern.DECIMAL_INT.getCategory());
            assertEquals(LiteralPattern.Category.INTEGER,
                    LiteralPattern.HEX_INT.getCategory());
            assertEquals(LiteralPattern.Category.INTEGER,
                    LiteralPattern.OCTAL_INT.getCategory());
        }
    }

    // ==================== TESTY LICZB ZMIENNOPRZECINKOWYCH ====================

    @Nested
    @DisplayName("Float Literals Tests")
    class FloatLiteralsTest {

        @ParameterizedTest
        @ValueSource(strings = {
                "0.0", "3.14", "123.456", "0.123", "999.999"
        })
        @DisplayName("Should match valid decimal floats")
        void shouldMatchValidDecimalFloats(String input) {
            assertTrue(LiteralPattern.FLOAT_DECIMAL.matches(input));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "1.5e10", "2.0E-5", "3.14159e+2", "0.5E0", "9.99e-10"
        })
        @DisplayName("Should match valid decimal floats with exponent")
        void shouldMatchValidDecimalFloatsWithExponent(String input) {
            assertTrue(LiteralPattern.FLOAT_DECIMAL_EXP.matches(input));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "1e10", "5E-3", "123e+5", "0e0", "999E999"
        })
        @DisplayName("Should match valid floats with exponent only")
        void shouldMatchValidFloatsWithExponentOnly(String input) {
            assertTrue(LiteralPattern.FLOAT_EXP_ONLY.matches(input));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "0x1.8p3", "0XA.Fp-2", "0x1A.BC0p+10",
                "0x.Fp5", "0xABC.p-5"
        })
        @DisplayName("Should match valid hexadecimal floats with fraction")
        void shouldMatchValidHexFloatsWithFraction(String input) {
            assertTrue(LiteralPattern.FLOAT_HEX_FRACTIONAL.matches(input));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "0x1p10", "0XFFp-4", "0xABCp+5", "0x0p0"
        })
        @DisplayName("Should match valid hexadecimal floats without fraction")
        void shouldMatchValidHexFloatsWithoutFraction(String input) {
            assertTrue(LiteralPattern.FLOAT_HEX_INT.matches(input));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "123",       // integer
                ".",         // just dot
                ".5",        // no leading digit
                "5.",        // no trailing digit (for FLOAT_DECIMAL)
                "1e",        // incomplete exponent
                "0x1p"       // incomplete hex float
        })
        @DisplayName("Should not match invalid floats")
        void shouldNotMatchInvalidFloats(String input) {
            assertFalse(LiteralPattern.FLOAT_DECIMAL.matches(input));
        }

        @Test
        @DisplayName("All float literals should be in FLOAT category")
        void allFloatLiteralsShouldBeInFloatCategory() {
            assertEquals(LiteralPattern.Category.FLOAT,
                    LiteralPattern.FLOAT_DECIMAL.getCategory());
            assertEquals(LiteralPattern.Category.FLOAT,
                    LiteralPattern.FLOAT_DECIMAL_EXP.getCategory());
            assertEquals(LiteralPattern.Category.FLOAT,
                    LiteralPattern.FLOAT_EXP_ONLY.getCategory());
            assertEquals(LiteralPattern.Category.FLOAT,
                    LiteralPattern.FLOAT_HEX_FRACTIONAL.getCategory());
            assertEquals(LiteralPattern.Category.FLOAT,
                    LiteralPattern.FLOAT_HEX_INT.getCategory());
        }
    }

    // ==================== TESTY WARTOŚCI LOGICZNYCH ====================

    @Nested
    @DisplayName("Boolean Literals Tests")
    class BooleanLiteralsTest {

        @Test
        @DisplayName("Should match 'true'")
        void shouldMatchTrue() {
            assertTrue(LiteralPattern.BOOL_TRUE.matches("true"));
        }

        @Test
        @DisplayName("Should match 'false'")
        void shouldMatchFalse() {
            assertTrue(LiteralPattern.BOOL_FALSE.matches("false"));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "True", "TRUE", "False", "FALSE", "1", "0", "yes", "no"
        })
        @DisplayName("Should not match invalid boolean values")
        void shouldNotMatchInvalidBooleans(String input) {
            assertFalse(LiteralPattern.BOOL_TRUE.matches(input));
            assertFalse(LiteralPattern.BOOL_FALSE.matches(input));
        }

        @Test
        @DisplayName("Boolean literals should be in BOOLEAN category")
        void booleanLiteralsShouldBeInBooleanCategory() {
            assertEquals(LiteralPattern.Category.BOOLEAN,
                    LiteralPattern.BOOL_TRUE.getCategory());
            assertEquals(LiteralPattern.Category.BOOLEAN,
                    LiteralPattern.BOOL_FALSE.getCategory());
        }
    }

    // ==================== TESTY IDENTYFIKATORÓW ====================

    @Nested
    @DisplayName("Identifier Tests")
    class IdentifierTest {

        @ParameterizedTest
        @ValueSource(strings = {
                "x", "myVariable", "my_variable_123", "_privateVar",
                "MAX_VALUE", "camelCase", "snake_case",
                "a1", "Z9", "aBcDeF123"
        })
        @DisplayName("Should match valid identifiers")
        void shouldMatchValidIdentifiers(String input) {
            assertTrue(LiteralPattern.IDENTIFIER.matches(input));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "123var",     // starts with digit
                "my-var",     // contains hyphen
                "my var",     // contains space
                "my.var",     // contains dot
                "",           // empty
                "123"         // only digits
        })
        @DisplayName("Should not match invalid identifiers")
        void shouldNotMatchInvalidIdentifiers(String input) {
            assertFalse(LiteralPattern.IDENTIFIER.matches(input));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "'for'", "'my-variable'", "'x + y'", "'123'",
                "'hello world'", "'_special_'"
        })
        @DisplayName("Should match valid quoted identifiers")
        void shouldMatchValidQuotedIdentifiers(String input) {
            assertTrue(LiteralPattern.QUOTED_IDENTIFIER.matches(input));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "for",           // no quotes
                "'unclosed",     // unclosed quote
                "unopened'",     // no opening quote
                "''",            // empty quotes
                "'new\nline'"    // contains newline
        })
        @DisplayName("Should not match invalid quoted identifiers")
        void shouldNotMatchInvalidQuotedIdentifiers(String input) {
            assertFalse(LiteralPattern.QUOTED_IDENTIFIER.matches(input));
        }

        @Test
        @DisplayName("Identifier literals should be in IDENTIFIER category")
        void identifierLiteralsShouldBeInIdentifierCategory() {
            assertEquals(LiteralPattern.Category.IDENTIFIER,
                    LiteralPattern.IDENTIFIER.getCategory());
            assertEquals(LiteralPattern.Category.IDENTIFIER,
                    LiteralPattern.QUOTED_IDENTIFIER.getCategory());
        }
    }

    // ==================== TESTY ZMIENNYCH TYPÓW ====================

    @Nested
    @DisplayName("Type Variable Tests")
    class TypeVariableTest {

        @ParameterizedTest
        @ValueSource(strings = {
                "$T", "$ElementType", "$T1", "$$", "$MyType_123",
                "$a", "$Z", "$ABC"
        })
        @DisplayName("Should match valid type variables")
        void shouldMatchValidTypeVariables(String input) {
            assertTrue(LiteralPattern.TYPE_VARIABLE.matches(input));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "$",          // just dollar sign
                "$123",       // starts with digit after $
                "T",          // no dollar sign
                "$ T",        // space after $
                "$-type",     // hyphen
                ""            // empty
        })
        @DisplayName("Should not match invalid type variables")
        void shouldNotMatchInvalidTypeVariables(String input) {
            assertFalse(LiteralPattern.TYPE_VARIABLE.matches(input));
        }

        @Test
        @DisplayName("Type variable should be in TYPE_VARIABLE category")
        void typeVariableShouldBeInTypeVariableCategory() {
            assertEquals(LiteralPattern.Category.TYPE_VARIABLE,
                    LiteralPattern.TYPE_VARIABLE.getCategory());
        }
    }

    // ==================== TESTY ZAWARTOŚCI STRINGÓW ====================

    @Nested
    @DisplayName("String Contents Tests")
    class StringContentsTest {

        @ParameterizedTest
        @ValueSource(strings = {
                "Hello World",
                "Line 1\\nLine 2",
                "He said \\\"Hi\\\"",
                "Path: C:\\\\Users\\\\name",
                "\\x41\\x42\\x43",
                "\\101\\102\\103",
                "",  // empty string is valid
                "Simple text without escapes",
                "_"
        })
        @DisplayName("Should match valid string contents")
        void shouldMatchValidStringContents(String input) {
            assertTrue(LiteralPattern.STRING_CONTENTS.matches(input));
        }

        @Test
        @DisplayName("String contents should be in STRING category")
        void stringContentsShouldBeInStringCategory() {
            assertEquals(LiteralPattern.Category.STRING,
                    LiteralPattern.STRING_CONTENTS.getCategory());
        }
    }

    // ==================== TESTY WILDCARDA ====================

    @Nested
    @DisplayName("Underscore/Wildcard Tests")
    class UnderscoreTest {

        @Test
        @DisplayName("Should match underscore")
        void shouldMatchUnderscore() {
            assertTrue(LiteralPattern.UNDERSCORE.matches("_"));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "__", "_a", "a_", " _", "_ "
        })
        @DisplayName("Should not match non-underscore strings")
        void shouldNotMatchNonUnderscore(String input) {
            assertFalse(LiteralPattern.UNDERSCORE.matches(input));
        }

        @Test
        @DisplayName("Underscore should be in SPECIAL category")
        void underscoreShouldBeInSpecialCategory() {
            assertEquals(LiteralPattern.Category.SPECIAL,
                    LiteralPattern.UNDERSCORE.getCategory());
        }
    }

    // ==================== TESTY METODY findAllMatching ====================

    @Nested
    @DisplayName("findAllMatching Method Tests")
    class FindAllMatchingMethodTest {

        @Test
        @DisplayName("Should find multiple matches for ambiguous input")
        void shouldFindMultipleMatchesForAmbiguousInput() {
            // "true" może pasować do BOOL_TRUE i IDENTIFIER
            List<LiteralPattern> matches = LiteralPattern.findAllMatching("true");

            assertFalse(matches.isEmpty());
            assertTrue(matches.contains(LiteralPattern.BOOL_TRUE));
            // W zależności od wzorca IDENTIFIER, może też do niego pasować
        }

        @Test
        @DisplayName("Should find single match for unambiguous input")
        void shouldFindSingleMatchForUnambiguousInput() {
            List<LiteralPattern> matches = LiteralPattern.findAllMatching("0xFF");

            assertFalse(matches.isEmpty());
            assertTrue(matches.contains(LiteralPattern.HEX_INT));
        }

        @Test
        @DisplayName("Should return empty list for non-matching input")
        void shouldReturnEmptyListForNonMatchingInput() {
            List<LiteralPattern> matches = LiteralPattern.findAllMatching("\\*inv");

            assertTrue(matches.isEmpty());
        }

        @Test
        @DisplayName("Should return unmodifiable list")
        void shouldReturnUnmodifiableList() {
            List<LiteralPattern> matches = LiteralPattern.findAllMatching("123");

            assertThrows(UnsupportedOperationException.class, () -> {
                matches.add(LiteralPattern.DECIMAL_INT);
            });
        }

        @Test
        @DisplayName("Should find underscore matching both UNDERSCORE and STRING_CONTENTS")
        void shouldFindUnderscoreMatchingBothPatterns() {
            List<LiteralPattern> matches = LiteralPattern.findAllMatching("_");
            assertFalse(matches.isEmpty());
            assertTrue(matches.contains(LiteralPattern.UNDERSCORE));
            assertTrue(matches.contains(LiteralPattern.STRING_CONTENTS));
        }

        @ParameterizedTest
        @CsvSource({
                "123, 2",      // DECIMAL_INT, STRING_CONTENTS
                "0xFF, 2",     // HEX_INT, STRING_CONTENTS
                "3.14, 2",     // FLOAT_DECIMAL, STRING_CONTENTS
                "true, 3",     // BOOL_TRUE, IDENTIFIER, STRING_CONTENTS
                "false, 3",    // BOOL_FALSE, IDENTIFIER, STRING_CONTENTS
                "_, 2",        // UNDERSCORE, STRING_CONTENTS
                "$T, 2"        // TYPE_VARIABLE, STRING_CONTENTS
        })
        @DisplayName("Should find expected number of matches")
        void shouldFindExpectedNumberOfMatches(String input, int expectedCount) {
            List<LiteralPattern> matches = LiteralPattern.findAllMatching(input);
            assertEquals(expectedCount, matches.size(),
                    "Expected " + expectedCount + " matches for '" + input + "'");
        }
    }
}