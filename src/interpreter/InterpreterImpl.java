package interpreter;

import parser.*;
import parser.Number;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Author: brianfroschauer
 * Date: 2019-06-06
 */
public class InterpreterImpl implements Interpreter, NodeVisitor {

    private final Map<String, Integer> symbolTable = new HashMap<>();
    private final Stack<Integer> operations = new Stack<>();

    @Override
    public void interpret(ASTNode node) {

        node.accept(this);
    }

    @Override
    public void visitNumber(Number node) {

        operations.push(Integer.valueOf(node.getValue()));
    }

    @Override
    public void visitBinaryOperation(BinaryOperation node) {

        switch (node.getValue()) {
            case "PLUS": {
                evaluatePlus(node);
                break;
            }
            case "MINUS": {
                evaluateMinus(node);
                break;
            }
            case "MUL": {
                evaluateMul(node);
                break;
            }
            default: {
                evaluateDiv(node);
                break;
            }
        }
    }

    @Override
    public void visitCompound(Compound node) {

        node.getChildren().forEach(children -> children.accept(this));
    }

    @Override
    public void visitAssign(Assign node) {

        final String varName = node.getLeft().getValue();
        node.getRight().accept(this);
        symbolTable.put(varName, operations.pop());
    }

    @Override
    public void visitDeclaration(Declaration node) {

        final String varName = node.getValue();
        symbolTable.put(varName, null);
    }

    @Override
    public void visitVar(Var node) {

        final String varName = node.getValue();
        final Integer val = symbolTable.get(varName);
        if (val != null) operations.push(val);
        else throw new RuntimeException("Undeclared variable");
    }

    @Override
    public void visitPrint(Print node) {
        node.getNode().accept(this);
        System.out.println(operations.pop());
    }

    private void evaluatePlus(BinaryOperation node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
        final Integer val1 = operations.pop();
        final Integer val2 = operations.pop();
        operations.push(val2 + val1);
    }

    private void evaluateMinus(BinaryOperation node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
        final Integer val1 = operations.pop();
        final Integer val2 = operations.pop();
        operations.push(val2 - val1);
    }

    private void evaluateMul(BinaryOperation node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
        final Integer val1 = operations.pop();
        final Integer val2 = operations.pop();
        operations.push(val2 * val1);
    }

    private void evaluateDiv(BinaryOperation node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
        final Integer val1 = operations.pop();
        final Integer val2 = operations.pop();
        operations.push(val2 / val1);
    }
}
