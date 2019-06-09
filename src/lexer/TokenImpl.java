package lexer;

/**
 * Author: brianfroschauer
 * Date: 2019-06-04
 */
public class TokenImpl implements Token {

    private final Kind kind;
    private final String value;

    TokenImpl(Kind kind, String value) {
        this.kind = kind;
        this.value = value;
    }

    @Override
    public Kind getKind() {
        return kind;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("TokenImpl(%s, %s)", kind.name(), value);
    }
}
