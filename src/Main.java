import interpreter.Interpreter;
import interpreter.InterpreterImpl;
import lexer.Lexer;
import lexer.LexerImpl;
import lexer.Token;
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

        final Lexer lexer = new LexerImpl();
        final Parser parser = new ParserImpl();
        final Interpreter interpreter = new InterpreterImpl();

        final List<Token> tokens = lexer.lex(
                 "let var1: number; " +
                        "var1 = 2 * (5 - 1); " +        // var1 = 2 * 4 = 8
                        "let var2: number; " +
                        "var2 = var1 - 5 * (2 - 3); " + // var2 = 8 - (5 * -1) = 8 - (-5) = 13
                        "print(var2 + 1); " +           // print(13 + 1)
                        "print(var1); " +               // print(8)
                        "let var3: string; " +
                        "var3 = 'Hello, '; " +
                        "print(var3 + 'world!');");     // print(Hello, world!)

        final ASTNode node = parser.parse(tokens);
        interpreter.interpret(node);
    }
}
