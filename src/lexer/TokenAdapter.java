package lexer;

import lexer2.TokenType;

import static lexer2.TokenType.STRING_LITERAL;

/**
 * Author: brianfroschauer
 * Date: 2019-06-11
 */
public class TokenAdapter implements Token {

    private lexer2.Token token;

    TokenAdapter(lexer2.Token token) {
        this.token = token;
    }

    @Override
    public Kind getKind() {

        final TokenType type = token.getType();

        Kind kind = null;

        switch (type) {
            case STRING_LITERAL: return Kind.STRING_LITERAL;
            case NUMBER_LITERAL: return Kind.NUMBER_LITERAL;
            case IDENTIFIER: return Kind.ID;
            case SPACE: break;
            case NEWLINE: break;
            case EOF: return Kind.EOF;
            case STRING_TYPE: return Kind.STRING_DATATYPE;
            case NUMBER_TYPE: return Kind.NUMBER_DATATYPE;
            case PLUS: return Kind.PLUS;
            case MINUS: return Kind.MINUS;
            case ASTERISK: return Kind.MUL;
            case FORWARD_SLASH: return Kind.DIV;
            case LEFT_PAREN: return Kind.LPAREN;
            case RIGHT_PAREN: return Kind.RPAREN;
            case EQUALS: return Kind.ASSIGN;
            case COLON: return Kind.COLON;
            case SEMICOLON: return Kind.SEMICOLON;
            case LET: return Kind.LET;
            case PRINT: return Kind.PRINT;
            default: throw new RuntimeException("Lexer exception");
        }
        return kind;
    }

    @Override
    public String getValue() {
        if (STRING_LITERAL == token.getType()) {
            return token.getLexeme().substring(1, token.getLexeme().length()-1);
        } return token.getLexeme();
    }
}
