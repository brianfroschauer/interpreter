package lexer2;

/**
 * @author Tomas Perez Molina
 */
public interface BasicToken {
    TokenType getType();

    String getLexeme();
}
