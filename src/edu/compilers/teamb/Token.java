package edu.compilers.teamb;

public class Token {
    private String lexeme;
    // TODO tokens. what else?

    public Token(String s) {
        this.lexeme = s;
    }

    public String getLexeme() {
        return lexeme;
    }

    @Override
    public String toString() {
        return lexeme;
    }
}
