package parser;

import lexer.Token;

/**
 * Author: brianfroschauer
 * Date: 2019-06-05
 */
public interface NonTerminalNode extends ASTNode {

    Token getToken();
    ASTNode getLeft();
    ASTNode getRight();
}
