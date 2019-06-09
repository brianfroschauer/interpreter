package interpreter;

import parser.nodes.*;
import parser.nodes.NumberLiteral;

/**
 * Author: brianfroschauer
 * Date: 2019-06-06
 */
public interface NodeVisitor {

    void visitCompound(Compound node);
    void visitAssignation(Assignation node);
    void visitDeclaration(Declaration node);
    void visitVariable(Variable node);
    void visitBinaryOperation(BinaryOperation node);
    void visitNumber(NumberLiteral node);
    void visitString(StringLiteral node);
    void visitPrint(Print node);
}
