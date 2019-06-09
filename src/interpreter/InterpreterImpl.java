package interpreter;

import parser.nodes.*;
import parser.nodes.NumberLiteral;

import java.util.Stack;

/**
 * Author: brianfroschauer
 * Date: 2019-06-06
 */
public class InterpreterImpl implements Interpreter, NodeVisitor {

    private final SymbolTable symbolTable = new SymbolTable();
    private final Stack<Integer> stack = new Stack<>();

    @Override
    public void interpret(ASTNode node) {

        node.accept(this);
    }

    @Override
    public void visitCompound(Compound node) {

        node.getChildren().forEach(children -> children.accept(this));
    }

    @Override
    public void visitAssignation(Assignation node) {

        final String varName = node.getLeft().getValue();
        final boolean isDefined = symbolTable.isDefined(varName);
        if (!isDefined) throw new RuntimeException("Undeclared variable");
        node.getRight().accept(this);
        symbolTable.define(varName, stack.pop());
    }

    @Override
    public void visitDeclaration(Declaration node) {

        final String varName = node.getValue();
        symbolTable.define(varName, null);
    }

    @Override
    public void visitVariable(Variable node) {

        final String varName = node.getValue();
        final Integer val = symbolTable.lookup(varName);
        if (val != null) stack.push(val);
        else throw new RuntimeException("Null pointer exception: " + varName);
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
    public void visitNumber(NumberLiteral node) {

        stack.push(Integer.valueOf(node.getValue()));
    }

    @Override
    public void visitPrint(Print node) {
        node.getNode().accept(this);
        System.out.println(stack.pop());
    }

    private void evaluatePlus(BinaryOperation node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
        final Integer val1 = stack.pop();
        final Integer val2 = stack.pop();
        stack.push(val2 + val1);
    }

    private void evaluateMinus(BinaryOperation node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
        final Integer val1 = stack.pop();
        final Integer val2 = stack.pop();
        stack.push(val2 - val1);
    }

    private void evaluateMul(BinaryOperation node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
        final Integer val1 = stack.pop();
        final Integer val2 = stack.pop();
        stack.push(val2 * val1);
    }

    private void evaluateDiv(BinaryOperation node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
        final Integer val1 = stack.pop();
        final Integer val2 = stack.pop();
        stack.push(val2 / val1);
    }
}
