import interpreter.Interpreter;
import interpreter.InterpreterImpl;
import lexer.Lexer;
import lexer.LexerImpl;
import lexer.Token;
import parser.ASTNode;
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

        final List<Token> tokens = lexer.lex("let var1; var1 = 2 * (5 - 1); var2 = var1 - 5 * (2 - 3); print(var2 + 1); print(var1);");
        final ASTNode node = parser.parse(tokens);
        interpreter.interpret(node);
    }
}
