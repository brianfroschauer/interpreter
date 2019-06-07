package interpreter;

import parser.BinaryOperation;
import parser.Number;

/**
 * Author: brianfroschauer
 * Date: 2019-06-06
 */
public interface NodeVisitor {

    Integer visitNumber(Number node);
    Integer visitBinaryOperation(BinaryOperation node);
}
