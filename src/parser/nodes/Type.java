package parser.nodes;

import interpreter.NodeVisitor;

/**
 * Author: brianfroschauer
 * Date: 2019-06-09
 */
public class Type implements ASTNode {

    private final String value;

    public Type(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void accept(NodeVisitor visitor) {

    }
}
