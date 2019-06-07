package parser;

import lexer.Token;

/**
 * Author: brianfroschauer
 * Date: 2019-06-05
 */
public interface TerminalNode extends ASTNode {

    Token getToken();
    String getValue();
}
