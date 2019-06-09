package parser;

import interpreter.NodeVisitor;

/**
 * Author: brianfroschauer
 * Date: 2019-06-06
 */
public class BinaryOperation implements ASTNode {

    private final String operation;
    private final ASTNode left;
    private final ASTNode right;

    BinaryOperation(String operation, ASTNode left, ASTNode right) {
        this.operation = operation;
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitBinaryOperation(this);
    }

    @Override
    public String getValue() {
        return operation;
    }

    public ASTNode getLeft() {
        return left;
    }

    public ASTNode getRight() {
        return right;
    }
}
