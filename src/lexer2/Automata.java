package lexer2;

/**
 * @author Tomas Perez Molina
 */
public interface Automata {
    boolean acceptable();
    void consume(char c);
    void reset();
}
