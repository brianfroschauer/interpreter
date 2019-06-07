package lexer;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: brianfroschauer
 * Date: 2019-06-04
 */
public class Lexer {

    private String input;
    private int pos;
    private Character current;

    public List<Token> tokenize(String input) {
        this.input = input;
        this.pos = 0;
        this.current = input.charAt(0);

        final List<Token> tokens = new ArrayList<>();

        while (current != null) {
            if (Character.isSpaceChar(current)) {
                consumeSpaces();
            } else if (Character.isDigit(current)) {
                tokens.add(new Token(Kind.INTEGER, integer()));
            } else if (current == '+') {
                tokens.add(new Token(Kind.PLUS, "+"));
                advance();
            } else if (current == '-') {
                tokens.add(new Token(Kind.MINUS, "-"));
                advance();
            } else if (current == '*') {
                tokens.add(new Token(Kind.MUL, "*"));
                advance();
            } else if (current == '/') {
                tokens.add(new Token(Kind.DIV, "/"));
                advance();
            } else if (current == '(') {
                tokens.add(new Token(Kind.LPAREN, "("));
                advance();
            } else if (current == ')') {
                tokens.add(new Token(Kind.RPAREN, ")"));
                advance();
            } else {
                throw new RuntimeException("Unrecognized token: " + current);
            }
        }

        tokens.add(new Token(Kind.EOF, null));

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

    private void consumeSpaces() {
        while (current != null && Character.isSpaceChar(current)) {
            advance();
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
