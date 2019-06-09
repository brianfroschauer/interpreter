import interpreter.Interpreter;
import lexer.Lexer;
import lexer.Token;
import parser.ASTNode;
import parser.Parser;

import java.util.List;
import java.util.Map;


/**
 * Author: brianfroschauer
 * Date: 2019-06-06
 */
public class Main {

    public static void main(String[] args) {

        final Lexer lexer = new Lexer();
        final Parser parser = new Parser();
        final Interpreter interpreter = new Interpreter();

        final List<Token> tokens = lexer.tokenize("let var1; var1 = 2 * (5 - 1); var2 = var1 - 5 * (2 - 3);");
        final ASTNode ast = parser.parse(tokens);
        final Map<String, Integer> table = interpreter.interpret(ast);
    }
}
