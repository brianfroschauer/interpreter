package parser;

import interpreter.NodeVisitor;
import lexer.Token;

import java.util.List;

/**
 * Author: brianfroschauer
 * Date: 2019-06-07
 */
public class Compound implements NonTerminalNode {

    private final List<ASTNode> children;

    public Compound(List<ASTNode> children) {
        this.children = children;
    }

    public List<ASTNode> getChildren() {
        return children;
    }

    @Override
    public Token getToken() {
        return null;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitCompound(this);
    }
}
