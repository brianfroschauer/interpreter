package parser;

import interpreter.NodeVisitor;

/**
 * Author: brianfroschauer
 * Date: 2019-06-07
 */
public class Assign implements ASTNode {

    private final String value;
    private final ASTNode left;
    private final ASTNode right;

    Assign(String value, ASTNode left, ASTNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitAssign(this);
    }

    @Override
    public String getValue() {
        return value;
    }

    public ASTNode getLeft() {
        return left;
    }

    public ASTNode getRight() {
        return right;
    }
}
