package parser;

import lexer.Kind;
import lexer.Token;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static lexer.Kind.*;

/**
 * Author: brianfroschauer
 * Date: 2019-06-06
 */
public class Parser {

    private Iterator<Token> iterator;
    private Token current;

    public ASTNode parse(List<Token> tokens) {
        iterator = tokens.iterator();
        current = iterator.next();
        return program();
    }

    /**
     * program: statement_list
     */
    private ASTNode program() {
        
        final List<ASTNode> nodes = statementList();
        return new Compound(nodes);
    }

    /**
     * statement_list: statement SEMI | statement SEMI statement_list
     */
    private List<ASTNode> statementList() {

        final ASTNode node = statement();

        eat(SEMI);

        final List<ASTNode> nodes = new ArrayList<>();

        nodes.add(node);

        while (current.getKind() != EOF) {
            nodes.add(statement());
            eat(SEMI);
        }

        return nodes;
    }

    /**
     * statement: assignment_statement | declaration_statement
     */
    private ASTNode statement() {

        ASTNode node;

        if (current.getKind().equals(LET)) {
            node = declarationStatement();
        } else {
            node = assignmentStatement();
        }
        return node;
    }

    /**
     * assignment_statement: variable ASSIGN expr
     */
    private ASTNode assignmentStatement() {

        final ASTNode left = variable();
        final Token token = current;
        eat(ASSIGN);
        final ASTNode right = expr();
        return new Assign(token, left, right);
    }

    /**
     * declaration_statement: LET variable
     */
    private ASTNode declarationStatement() {

        eat(LET);
        final Token token = current;
        return new Declaration(token, variable());
    }

    /**
     *  variable: ID
     */
    private ASTNode variable() {

        final ASTNode variable = new Var(current, current.getValue());
        eat(ID);
        return variable;
    }

    /**
     * factor : INTEGER | LPAREN expr RPAREN | variable
     */
    private ASTNode factor() {

        final Token token = current;

        if (token.getKind().equals(INTEGER)) {
            eat(INTEGER);
            return new Number(token, token.getValue());
        } else if (token.getKind().equals(LPAREN)) {
            eat(LPAREN);
            ASTNode node = expr();
            eat(RPAREN);
            return node;
        } else {
            return variable();
        }
    }

    /**
     * term : factor ((MUL | DIV) factor)*
     */
    private ASTNode term() {

        ASTNode node = factor();

        while (current.getKind().equals(MUL) || current.getKind().equals(DIV)) {
            Token current = this.current;
            if (current.getKind().equals(MUL)) {
                eat(MUL);
            } else {
                eat(DIV);
            }
            node = new BinaryOperation(current, node, factor());
        }

        return node;
    }

    /**
     * expr   : term ((PLUS | MINUS) term)*
     * term   : factor ((MUL | DIV) factor)*
     * factor : INTEGER | LPAREN expr RPAREN
     */
    private ASTNode expr() {

        ASTNode node = term();

        while (current.getKind().equals(PLUS) || current.getKind().equals(MINUS)) {
            Token current = this.current;
            if (current.getKind().equals(PLUS)) {
                eat(PLUS);
            } else {
                eat(MINUS);
            }
            node = new BinaryOperation(current, node, term());
        }
        return node;
    }

    private void eat(Kind kind) {

        if (current.getKind().equals(kind)) {
            current = iterator.next();
        } else throw new RuntimeException("Invalid syntax");
    }
}
