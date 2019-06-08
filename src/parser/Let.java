package parser;

import interpreter.NodeVisitor;
import lexer.Token;

/**
 * Author: brianfroschauer
 * Date: 2019-06-07
 */
public class Let implements TerminalNode {

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public Integer accept(NodeVisitor visitor) {
        return null;
    }

    @Override
    public Token getToken() {
        return null;
    }
}
