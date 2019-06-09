package parser.nodes;

import interpreter.NodeVisitor;

/**
 * Author: brianfroschauer
 * Date: 2019-06-09
 */
public class Print implements ASTNode {

    private final ASTNode node;

    public Print(ASTNode node) {
        this.node = node;
    }

    public ASTNode getNode() {
        return node;
    }

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitPrint(this);
    }
}
