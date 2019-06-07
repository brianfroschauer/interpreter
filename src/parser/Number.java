package parser;

import interprete.visitor.NodeVisitor;
import lexer.Token;

/**
 * Author: brianfroschauer
 * Date: 2019-06-06
 */
public class Number implements TerminalNode {

    private final Token token;
    private final String number;

    public Number(Token token, String number) {
        this.token = token;
        this.number = number;
    }

    @Override
    public Token getToken() {
        return token;
    }

    @Override
    public String getValue() {
        return number;
    }

    @Override
    public Integer accept(NodeVisitor visitor) {
        return visitor.visitNumber(this);
    }
}
