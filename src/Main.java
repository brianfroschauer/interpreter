import interpreter.Interpreter;
import interpreter.InterpreterImpl;
import lexer.Lexer;
import lexer.LexerAdapter;
import lexer.Token;
import lexer2.LexerFactory;
import parser.nodes.ASTNode;
import parser.Parser;
import parser.ParserImpl;

import java.util.List;


/**
 * Author: brianfroschauer
 * Date: 2019-06-06
 */
public class Main {

    public static void main(String[] args) {

        final lexer2.Lexer tsLexer = new LexerFactory().getTSLexer();
        final Lexer adapter = new LexerAdapter(tsLexer);
        final Parser parser = new ParserImpl();
        final Interpreter interpreter = new InterpreterImpl();

        final List<Token> tokens = adapter.lex(
                   "let var1: number; " +
                         "\nlet var2: string;" +

                         "\nvar2 = 'Hello, ';" +
                         "\nvar1 = 2 * (5 - 1);" +

                         "\nprint(var1);" +

                         "\nprint(var2 + 'World!');" +
                         "\nprint(var1 * 2);");

        final ASTNode node = parser.parse(tokens);
        interpreter.interpret(node);
    }
}
