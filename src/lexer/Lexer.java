package lexer;

import java.util.List;

/**
 * Author: brianfroschauer
 * Date: 2019-06-09
 */
public interface Lexer {

    List<Token> lex(String input);
}
