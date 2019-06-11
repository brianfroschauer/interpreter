package lexer2;

/**
 * @author Tomas Perez Molina
 */
@FunctionalInterface
public interface CharAcceptor {
    boolean accepts(char c);
}
