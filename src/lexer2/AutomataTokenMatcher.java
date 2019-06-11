package lexer2;

public class AutomataTokenMatcher implements TokenMatcher {
    private final Automata automata;
    private final TokenType tokenType;
    private String match = "";

    public AutomataTokenMatcher(TokenType tokenType, Automata automata) {
        this.automata = automata;
        this.tokenType = tokenType;
    }

    @Override
    public boolean isMatching() {
        return match.length() > 0;
    }

    @Override
    public boolean match(char c) {
        try {
            automata.consume(c);
            match = match + c;
            return true;
        } catch (NoTransitionException exc) {
            return false;
        }
    }

    @Override
    public BasicToken getBasicToken() {
        if (automata.acceptable()) {
            return new BasicTokenImpl(tokenType, match);
        } else {
            throw new NoMatchException(tokenType, match);
        }
    }

    @Override
    public boolean acceptable() {
        return automata.acceptable();
    }

    @Override
    public void reset() {
        match = "";
        automata.reset();
    }
}
