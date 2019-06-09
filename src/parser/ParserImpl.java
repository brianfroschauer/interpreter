package parser;

import lexer.Kind;
import lexer.Token;
import parser.nodes.*;
import parser.nodes.NumberLiteral;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static lexer.Kind.*;

/**
 * Author: brianfroschauer
 * Date: 2019-06-06
 */
public class ParserImpl implements Parser {

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
     * statement_list: statement SEMICOLON | statement SEMICOLON statement_list
     */
    private List<ASTNode> statementList() {

        final ASTNode node = statement();

        eat(SEMICOLON);

        final List<ASTNode> nodes = new ArrayList<>();

        nodes.add(node);

        while (current.getKind() != EOF) {
            nodes.add(statement());
            eat(SEMICOLON);
        }

        return nodes;
    }

    /**
     * statement: declaration_statement | print_statement | assignment_statement
     */
    private ASTNode statement() {

        ASTNode node;

        if (current.getKind().equals(LET)) {
            node = declarationStatement();
        } else if (current.getKind().equals(PRINT)) {
            node = printStatement();
        } else {
            node = assignmentStatement();
        }
        return node;
    }

    /**
     * declaration_statement: LET variable
     */
    private Declaration declarationStatement() {

        eat(LET);

        return new Declaration(variable().getValue());
    }

    /**
     * print_statement = PRINT LPAREN expression RPAREN
     */
    private ASTNode printStatement() {

        eat(PRINT);

        eat(LPAREN);

        final ASTNode expression = expression();

        eat(RPAREN);

        return new Print(expression);
    }

    /**
     * assignment_statement: variable ASSIGN expression
     */
    private Assignation assignmentStatement() {

        final Variable variable = variable();

        eat(ASSIGN);

        final ASTNode expression = expression();

        return new Assignation(current.getValue(), variable, expression);
    }

    /**
     * expression: term ((PLUS | MINUS) term)*
     * term: factor ((MUL | DIV) factor)*
     * factor: NUMBER_LITERAL | LPAREN expression RPAREN
     */
    private ASTNode expression() {

        ASTNode node = term();

        while (current.getKind().equals(PLUS) || current.getKind().equals(MINUS)) {
            Token current = this.current;
            if (current.getKind().equals(PLUS)) {
                eat(PLUS);
            } else {
                eat(MINUS);
            }
            node = new BinaryOperation(current.getKind().name(), node, term());
        }
        return node;
    }

    /**
     * term: factor ((MUL | DIV) factor)*
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
            node = new BinaryOperation(current.getKind().name(), node, factor());
        }

        return node;
    }

    /**
     * factor: NUMBER_LITERAL | LPAREN expression RPAREN | variable
     */
    private ASTNode factor() {

        final Token token = current;

        if (token.getKind().equals(NUMBER_LITERAL)) {
            eat(NUMBER_LITERAL);
            return new NumberLiteral(token.getValue());
        } else if (token.getKind().equals(LPAREN)) {
            eat(LPAREN);
            ASTNode node = expression();
            eat(RPAREN);
            return node;
        } else {
            return variable();
        }
    }

    /**
     *  variable: ID
     */
    private Variable variable() {

        final Variable variable = new Variable(current.getValue());

        eat(ID);

        return variable;
    }

    private void eat(Kind kind) {

        if (current.getKind().equals(kind)) {
            current = iterator.next();
        } else {
            throw new RuntimeException("Invalid syntax");
        }
    }
}
