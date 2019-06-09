package parser;

import interpreter.NodeVisitor;

import java.util.List;

/**
 * Author: brianfroschauer
 * Date: 2019-06-07
 */
public class Compound implements ASTNode {

    private final List<ASTNode> children;

    Compound(List<ASTNode> children) {
        this.children = children;
    }

    public List<ASTNode> getChildren() {
        return children;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitCompound(this);
    }

    @Override
    public String getValue() {
        return null;
    }
}
