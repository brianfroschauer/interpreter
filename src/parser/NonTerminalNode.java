package parser;

/**
 * Author: brianfroschauer
 * Date: 2019-06-05
 */
public interface NonTerminalNode extends ASTNode {

    ASTNode getLeft();
    ASTNode getRight();
}
