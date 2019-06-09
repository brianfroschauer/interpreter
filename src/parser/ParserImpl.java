package parser;

import lexer.Kind;
import lexer.Token;
import lexer.TokenImpl;

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
     * print_statement = PRINT LPAREN expr RPAREN
     */
    private ASTNode printStatement() {

        eat(PRINT);
        eat(LPAREN);
        final ASTNode factor = expr();
        eat(RPAREN);
        return new Print(factor);
    }

    /**
     * assignment_statement: variable ASSIGN expr
     */
    private Assign assignmentStatement() {

        final ASTNode left = variable();
        eat(ASSIGN);
        final ASTNode right = expr();
        return new Assign(current.getValue(), left, right);
    }

    /**
     *  variable: ID
     */
    private Var variable() {

        final Var variable = new Var(current.getValue());
        eat(ID);
        return variable;
    }

    /**
     * factor : NUMBER | LPAREN expr RPAREN | variable
     */
    private ASTNode factor() {

        final Token token = current;

        if (token.getKind().equals(NUMBER)) {
            eat(NUMBER);
            return new Number(token.getValue());
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
            node = new BinaryOperation(current.getKind().name(), node, factor());
        }

        return node;
    }

    /**
     * expr   : term ((PLUS | MINUS) term)*
     * term   : factor ((MUL | DIV) factor)*
     * factor : NUMBER | LPAREN expr RPAREN
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
            node = new BinaryOperation(current.getKind().name(), node, term());
        }
        return node;
    }

    private void eat(Kind kind) {

        if (current.getKind().equals(kind)) {
            current = iterator.next();
        } else {
            throw new RuntimeException("Invalid syntax");
        }
    }
}
