package parser;

import lexer.Token;
import parser.nodes.ASTNode;

import java.util.List;

/**
 * Author: brianfroschauer
 * Date: 2019-06-09
 */
public interface Parser {

    ASTNode parse(List<Token> tokens);
}
