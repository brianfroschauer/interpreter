package interpreter;

import parser.ASTNode;

/**
 * Author: brianfroschauer
 * Date: 2019-06-09
 */
public interface Interpreter {

    void interpret(ASTNode node);
}
