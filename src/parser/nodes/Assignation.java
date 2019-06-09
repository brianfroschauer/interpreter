package parser.nodes;

import interpreter.NodeVisitor;

/**
 * Author: brianfroschauer
 * Date: 2019-06-07
 */
public class Assignation implements BinaryNode {

    private final String value;
    private final Variable left;
    private final ASTNode right;

    public Assignation(String value, Variable left, ASTNode right) {
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
        visitor.visitAssignation(this);
    }
}
