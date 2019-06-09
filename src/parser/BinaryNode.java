package parser;

/**
 * Author: brianfroschauer
 * Date: 2019-06-09
 */
public interface BinaryNode extends ASTNode {

    ASTNode getLeft();
    ASTNode getRight();
}
