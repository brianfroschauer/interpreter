package parser;

import lexer.Kind;
import lexer.Token;

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
        return expr();
    }

    /**
     * factor : INTEGER | LPAREN expr RPAREN
     */
    private ASTNode factor() {

        final Token token = current;

        if (token.getKind().equals(INTEGER)) {
            eat(INTEGER);
            return new Number(current, token.getValue());
        } else {
            eat(LPAREN);
            ASTNode node = expr();
            eat(RPAREN);
            return node;
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
