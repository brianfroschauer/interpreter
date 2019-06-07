package interprete;

import parser.ASTNode;
import parser.BinaryOperation;
import parser.Number;

import static lexer.Kind.*;

/**
 * Author: brianfroschauer
 * Date: 2019-06-06
 */
public class Interpreter implements NodeVisitor {

    public Integer interpret(ASTNode node) {
        return node.accept(this);
    }

    @Override
    public Integer visitNumber(Number node) {
        return Integer.valueOf(node.getValue());
    }

    @Override
    public Integer visitBinaryOperation(BinaryOperation node) {
        if (node.getToken().getKind().equals(PLUS)) {
            return node.getLeft().accept(this) + node.getRight().accept(this);
        } else if (node.getToken().getKind().equals(MINUS)) {
            return node.getLeft().accept(this) - node.getRight().accept(this);
        } else if (node.getToken().getKind().equals(MUL)) {
            return node.getLeft().accept(this) * node.getRight().accept(this);
        } else {
            return node.getLeft().accept(this) / node.getRight().accept(this);
        }
    }
}
