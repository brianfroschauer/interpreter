package lexer;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: brianfroschauer
 * Date: 2019-06-11
 */
public class LexerAdapter implements Lexer {

    private lexer2.Lexer lexer;

    public LexerAdapter(lexer2.Lexer lexer) {
        this.lexer = lexer;
    }

    @Override
    public List<Token> lex(String input) {
        final List<lexer2.Token> tokens = lexer.lex(input);
        return tokens
                .stream()
                .map(token -> new TokenAdapter(token))
                .filter(token -> token.getKind() != null).collect(Collectors.toList());
    }
}
