package edu.compilers.teamb;


public class Translator {
    private String roman;
    private int arabic;

    private LexicalAnalyzer lexicalAnalyzer;
    private Parser parser;
    private IntermediateCodeGenerator intermediateCodeGenerator;
    private Evaluator evaluator;

    public Translator() {
        this.roman = "";
        this.arabic = 0;
        lexicalAnalyzer = new LexicalAnalyzer();
        parser = new Parser();
    }

    public void translate(String roman) {
        this.roman = roman;
        translate();
    }

    public String getArabic() {
        return Integer.toString(arabic);
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

        // generate intermediate code
        intermediateCodeGenerator.generate(parser.getParseTree());

        // evaluate by executing the intermediate code
        arabic = evaluator.evaluate(intermediateCodeGenerator.getCode());
    }
}
