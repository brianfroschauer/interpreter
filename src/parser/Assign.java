package parser;

import interpreter.NodeVisitor;
import lexer.Token;

/**
 * Author: brianfroschauer
 * Date: 2019-06-07
 */
public class Assign implements NonTerminalNode {

    private final Token token;
    private final ASTNode left;
    private final ASTNode right;

    public Assign(Token token, ASTNode left, ASTNode right) {
        this.token = token;
        this.left = left;
        this.right = right;
    }

    @Override
    public Token getToken() {
        return token;
    }

    public ASTNode getLeft() {
        return left;
    }

    public ASTNode getRight() {
        return right;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitAssign(this);
    }
}
