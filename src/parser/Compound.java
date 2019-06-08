package parser;

import interpreter.NodeVisitor;
import lexer.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: brianfroschauer
 * Date: 2019-06-07
 */
public class Compound implements ASTNode {

    List<ASTNode> children = new ArrayList<>();

    public void addChildren(ASTNode node) {
        children.add(node);
    }

    public List<ASTNode> getChildren() {
        return children;
    }

    @Override
    public Integer accept(NodeVisitor visitor) {
        return null;
    }

    @Override
    public Token getToken() {
        return null;
    }
}
