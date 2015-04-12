package edu.compilers.teamb;

import java.util.ArrayList;

public class Translator {
    private String roman;
    private String arabic;

    private LexicalAnalyzer lexicalAnalyzer;
    private Parser parser;

    public Translator() {
        this.roman = "";
        this.arabic = "NOT SET";
        lexicalAnalyzer = new LexicalAnalyzer();
        parser = new Parser();
    }

    public void translate(String roman) {
        this.roman = roman;
        translate();
    }

    public String getArabic() {
        return arabic;
    }

    private void translate() {

        // perform lexical analysis
        try {
            lexicalAnalyzer.analyze(roman);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }

        // create a parse tree
        parser.parse(lexicalAnalyzer.getTokens());

    }
}
