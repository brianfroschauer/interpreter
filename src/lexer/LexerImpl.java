package lexer;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: brianfroschauer
 * Date: 2019-06-04
 */
public class LexerImpl implements Lexer {

    private String input;
    private int pos;
    private Character current;

    @Override
    public List<Token> lex(String input) {
        this.input = input;
        this.pos = 0;
        this.current = input.charAt(0);

        final List<Token> tokens = new ArrayList<>();

        while (current != null) {
            if (Character.isSpaceChar(current)) {
                skipWitheSpaces();
            } else if (Character.isDigit(current)) {
                tokens.add(new TokenImpl(Kind.NUMBER, integer()));
            } else if (Character.isAlphabetic(current)) {
                tokens.add(identifierOrKeyword());
            } else if (current == '+') {
                tokens.add(new TokenImpl(Kind.PLUS, "+"));
                advance();
            } else if (current == '-') {
                tokens.add(new TokenImpl(Kind.MINUS, "-"));
                advance();
            } else if (current == '*') {
                tokens.add(new TokenImpl(Kind.MUL, "*"));
                advance();
            } else if (current == '/') {
                tokens.add(new TokenImpl(Kind.DIV, "/"));
                advance();
            } else if (current == '=') {
                tokens.add(new TokenImpl(Kind.ASSIGN, "="));
                advance();
            } else if (current == '(') {
                tokens.add(new TokenImpl(Kind.LPAREN, "("));
                advance();
            } else if (current == ')') {
                tokens.add(new TokenImpl(Kind.RPAREN, ")"));
                advance();
            } else if (current == ';') {
                tokens.add(new TokenImpl(Kind.SEMI, ";"));
                advance();
            } else if (current == '"' || current == '\'') {
                tokens.add(new TokenImpl(Kind.STRING, consumeString(current)));
                advance();
            } else {
                throw new RuntimeException("Unrecognized token: " + current);
            }
        }

        tokens.add(new TokenImpl(Kind.EOF, null));

        return tokens;
    }

    private void advance() {

        pos++;

        if (pos > input.length() - 1) {
            current = null;
        } else {
            current = input.charAt(pos);
        }
    }

    private void skipWitheSpaces() {

        while (current != null && Character.isSpaceChar(current)) {
            advance();
        }
    }

    private String consumeString(char type) {

        final StringBuilder sb = new StringBuilder();

        advance();

        while (current != null && current != type) {
            sb.append(current);
            advance();
        }

        return sb.toString();
    }

    private TokenImpl identifierOrKeyword() {

        final StringBuilder sb = new StringBuilder();

        while (current != null && Character.isLetterOrDigit(current)) {
            sb.append(current);
            advance();
        }

        final String result = sb.toString();

        if (result.equals("let")) return new TokenImpl(Kind.LET, result);
        if (result.equals("print")) return new TokenImpl(Kind.PRINT, result);

        else return new TokenImpl(Kind.ID, result);
    }

    private Character peek() {

        final int peekPos = pos + 1;

        if (peekPos > input.length() - 1) {
            return null;
        } else {
            return input.charAt(peekPos);
        }
    }

    private String integer() {

        final StringBuilder result = new StringBuilder();

        while (current != null && Character.isDigit(current)) {
            result.append(current);
            advance();
        }

        return result.toString();
    }
}
