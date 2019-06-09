package parser.nodes;

import interpreter.NodeVisitor;

/**
 * Author: brianfroschauer
 * Date: 2019-06-06
 */
public class NumberLiteral implements ASTNode {

    private final String value;

    public NumberLiteral(String value) {
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
