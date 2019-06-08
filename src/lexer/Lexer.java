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
                skipWitheSpaces();
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
            } else if (current == '=') {
                tokens.add(new Token(Kind.ASSIGN, "="));
                advance();
            } else if (current == ';') {
                tokens.add(new Token(Kind.SEMI, ";"));
                advance();
            } else if (current == '"' || current == '\'') {
                tokens.add(new Token(Kind.STRING, consumeString(current)));
                advance();
            } else if (Character.isAlphabetic(current)) {
                tokens.add(identifierOrKeyword());
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

    private Token identifierOrKeyword() {
        final StringBuilder sb = new StringBuilder();
        while (current != null && Character.isLetterOrDigit(current)) {
            sb.append(current);
            advance();
        }

        final String result = sb.toString();

        if (result.equals("let")) return new Token(Kind.LET, result);
        else return new Token(Kind.ID, result);
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
