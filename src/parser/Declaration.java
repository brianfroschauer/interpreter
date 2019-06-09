package parser;

import interpreter.NodeVisitor;
import lexer.Token;

/**
 * Author: brianfroschauer
 * Date: 2019-06-08
 */
public class Declaration implements TerminalNode {

    private final Token token;
    private final ASTNode var;

    public Declaration(Token token, ASTNode var) {
        this.token = token;
        this.var = var;
    }

    @Override
    public String getValue() {
        return var.getToken().getValue();
    }

    @Override
    public Token getToken() {
        return token;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitDeclaration(this);
    }
}
