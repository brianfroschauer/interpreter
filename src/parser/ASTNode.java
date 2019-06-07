package parser;

import interprete.visitor.NodeVisitable;
import lexer.Token;

/**
 * Author: brianfroschauer
 * Date: 2019-06-04
 */
public interface ASTNode extends NodeVisitable {

    Token getToken();
}
