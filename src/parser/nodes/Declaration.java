package parser.nodes;

import interpreter.NodeVisitor;

/**
 * Author: brianfroschauer
 * Date: 2019-06-08
 */
public class Declaration implements BinaryNode {

    private final Variable variable;
    private final Type type;

    public Declaration(Variable variable, Type type) {
        this.variable = variable;
        this.type = type;
    }

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public ASTNode getLeft() {
        return variable;
    }

    @Override
    public ASTNode getRight() {
        return type;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitDeclaration(this);
    }
}
