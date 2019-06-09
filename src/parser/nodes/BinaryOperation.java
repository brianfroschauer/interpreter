package parser.nodes;

import interpreter.NodeVisitor;

/**
 * Author: brianfroschauer
 * Date: 2019-06-06
 */
public class BinaryOperation implements BinaryNode {

    private final String value;
    private final ASTNode left;
    private final ASTNode right;

    public BinaryOperation(String value, ASTNode left, ASTNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public ASTNode getLeft() {
        return left;
    }

    @Override
    public ASTNode getRight() {
        return right;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitBinaryOperation(this);
    }
}
