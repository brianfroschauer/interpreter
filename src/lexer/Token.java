package lexer;

/**
 * Author: brianfroschauer
 * Date: 2019-06-04
 */
public class Token {

    private final Kind kind;
    private final String value;

    public Token(Kind kind, String value) {
        this.kind = kind;
        this.value = value;
    }

    public Kind getKind() {
        return kind;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("Token(%s, %s)", kind.name(), value);
    }
}
