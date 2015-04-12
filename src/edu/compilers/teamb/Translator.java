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
        try {
            lexicalAnalyzer.analyze(roman);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        ArrayList<Token> tokens = lexicalAnalyzer.getTokens();
        System.out.println(tokens);
    }
}
