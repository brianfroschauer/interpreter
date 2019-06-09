package parser.nodes;

import interpreter.NodeVisitor;

/**
 * Author: brianfroschauer
 * Date: 2019-06-09
 */
public class StringLiteral implements ASTNode {

    private final String value;

    public StringLiteral(String value) {
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
