package edu.compilers.teamb;

import java.util.ArrayList;

public class Translator {
    private String roman;
    private String arabic;

    private LexicalAnalyzer lexicalAnalyzer;

    public Translator() {
        this.roman = "";
        this.arabic = "NOT SET";
        lexicalAnalyzer = new LexicalAnalyzer();
    }

    public void translate(String roman) {
        this.roman = roman;
        translate();
    }

    public String getArabic() {
        return arabic;
    }

    private void translate() {
        lexicalAnalyzer.analyze(roman);
        ArrayList<Token> tokens = lexicalAnalyzer.getTokens();
        // TODO use tokens, and the rest

    }
}
