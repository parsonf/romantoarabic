package edu.compilers.teamb;

/**
 * Represents a token.
 */
public class Token {
    private String lexeme;

    /**
     * Constructs a token object with a given lexeme.
     *
     * @param s the lexeme.
     */
    public Token(String s) {
        this.lexeme = s;
    }

    /**
     * Returns the lexeme.
     *
     * @return the lexeme.
     */
    public String getLexeme() {
        return lexeme;
    }

    /**
     * Returns the String representation of this object.
     *
     * @return the String representation of this object.
     */
    @Override
    public String toString() {
        return lexeme;
    }
}
