package lexer2;

import java.util.List;

/**
 * @author Tomas Perez Molina
 */
public interface Lexer {
    List<Token> lex(String input);
}
