package parser;

import interpreter.NodeVisitor;
import lexer.Token;

/**
 * Author: brianfroschauer
 * Date: 2019-06-06
 */
public class BinaryOperation implements NonTerminalNode {

    private final Token token;
    private final ASTNode left;
    private final ASTNode right;

    public BinaryOperation(Token token, ASTNode left, ASTNode right) {
        this.left = left;
        this.token = token;
        this.right = right;
    }

    @Override
    public Token getToken() {
        return token;
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
    public Integer accept(NodeVisitor visitor) {
        return visitor.visitBinaryOperation(this);
    }
}
