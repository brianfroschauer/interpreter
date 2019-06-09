package interpreter;

import parser.*;
import parser.Number;

/**
 * Author: brianfroschauer
 * Date: 2019-06-06
 */
public interface NodeVisitor {

    void visitNumber(Number node);
    void visitBinaryOperation(BinaryOperation node);
    void visitCompound(Compound node);
    void visitAssign(Assign node);
    void visitDeclaration(Declaration node);
    void visitVar(Var node);
}
