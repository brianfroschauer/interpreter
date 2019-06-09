package parser;

import interpreter.NodeVisitor;

/**
 * Author: brianfroschauer
 * Date: 2019-06-07
 */
public class Var implements TerminalNode {

    private final String value;

    Var(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitVar(this);
    }
}
