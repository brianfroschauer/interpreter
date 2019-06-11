package lexer2;

/**
 * @author Tomas Perez Molina
 */
public interface AutomataState {
    boolean isAcceptable();
    AutomataState transition(char c);
}
