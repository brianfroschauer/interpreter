package parser;

import interpreter.NodeVisitor;

/**
 * Author: brianfroschauer
 * Date: 2019-06-06
 */
public class Number implements TerminalNode {

    private final String value;

    Number(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitNumber(this);
    }
}
