package parser;

import interpreter.NodeVisitor;
import lexer.Token;

/**
 * Author: brianfroschauer
 * Date: 2019-06-06
 */
public class Number implements TerminalNode {

    private final Token token;
    private final String value;

    public Number(Token token, String value) {
        this.token = token;
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Token getToken() {
        return token;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitNumber(this);
    }
}
