package lexer2;

public interface TokenMatcher {
    boolean isMatching();
    boolean match(char c);
    boolean acceptable();
    BasicToken getBasicToken();
    void reset();
}
