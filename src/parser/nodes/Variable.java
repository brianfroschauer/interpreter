package parser.nodes;

import interpreter.NodeVisitor;

/**
 * Author: brianfroschauer
 * Date: 2019-06-07
 */
public class Variable implements ASTNode {

    private final String value;

    public Variable(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitVariable(this);
    }
}
