import interpreter.Interpreter;
import lexer.Lexer;
import lexer.Token;
import parser.ASTNode;
import parser.Parser;

import java.util.List;
import java.util.Scanner;

/**
 * Author: brianfroschauer
 * Date: 2019-06-06
 */
public class Main {

    public static void main(String[] args) {

        do {

            final Lexer lexer = new Lexer();
            final Parser parser = new Parser();
            final Interpreter interpreter = new Interpreter();

            System.out.print("> ");

            final String input = new Scanner(System.in).nextLine();

            if (input.equals("exit")) break;
            if (input.equals("")) continue;
            if (input.equals("clear")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                continue;
            }

            Integer result = null;

            try {
                final List<Token> tokens = lexer.tokenize(input);
                final ASTNode tree = parser.parse(tokens);
                result = interpreter.interpret(tree);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }

            if (result != null) {
                System.out.println(result);
            }
        } while (true);
    }
}
