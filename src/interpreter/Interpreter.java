package interpreter;

import parser.*;
import parser.Number;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static lexer.Kind.*;

/**
 * Author: brianfroschauer
 * Date: 2019-06-06
 */
public class Interpreter implements NodeVisitor {

    private final Map<String, Integer> symbolTable = new HashMap<>();
    private final Stack<Integer> operations = new Stack<>();

    public Map<String, Integer> interpret(ASTNode node) {
        node.accept(this);
        return symbolTable;
    }

    @Override
    public void visitNumber(Number node) {

        operations.push(Integer.valueOf(node.getValue()));
    }

    @Override
    public void visitBinaryOperation(BinaryOperation node) {

        if (node.getToken().getKind().equals(PLUS)) {
            node.getLeft().accept(this);
            node.getRight().accept(this);
            final Integer val1 = operations.pop();
            final Integer val2 = operations.pop();
            operations.push(val2 + val1);
        } else if (node.getToken().getKind().equals(MINUS)) {
            node.getLeft().accept(this);
            node.getRight().accept(this);
            final Integer val1 = operations.pop();
            final Integer val2 = operations.pop();
            operations.push(val2 - val1);
        } else if (node.getToken().getKind().equals(MUL)) {
            node.getLeft().accept(this);
            node.getRight().accept(this);
            final Integer val1 = operations.pop();
            final Integer val2 = operations.pop();
            operations.push(val2 * val1);
        } else {
            node.getLeft().accept(this);
            node.getRight().accept(this);
            final Integer val1 = operations.pop();
            final Integer val2 = operations.pop();
            operations.push(val2 / val1);
        }
    }

    public Stack<Integer> getOperations() {
        return operations;
    }

    @Override
    public void visitCompound(Compound node) {

        node.getChildren().forEach(children -> children.accept(this));
    }

    @Override
    public void visitAssign(Assign node) {

        final String varName = node.getLeft().getToken().getValue();
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
}
