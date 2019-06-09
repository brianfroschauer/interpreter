package parser;

import interpreter.NodeVisitor;

/**
 * Author: brianfroschauer
 * Date: 2019-06-09
 */
public class Print implements ASTNode {

    private final ASTNode node;

    Print(ASTNode node) {
        this.node = node;
    }

    public ASTNode getNode() {
        return node;
    }

    @Override
    public String getValue() {
        return "print";
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitPrint(this);
    }
}
