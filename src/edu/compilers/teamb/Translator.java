package edu.compilers.teamb;


import java.util.ArrayList;
import java.util.HashMap;

public class Translator {
    public static final String TAG = "Translator";

    private HashMap<String,ArrayList<String>> allTranslationSteps = new HashMap<>();
    private ArrayList<String> translationSteps = new ArrayList<>();
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
        intermediateCodeGenerator = new IntermediateCodeGenerator();
        evaluator = new Evaluator();
    }
    /*
    Dereck Britton - moved translate call to UI class for exception handling
    public void translate(String roman) {
        this.roman = roman;

        translate();
    }
    */

    public String getArabic() {
        return Integer.toString(arabic);
    }

    //Dereck Britton - changed public call to this directly
    public void translate(String _roman) throws RomanTranslationException {
        //Saved class variable for usage later - its only use is for translation, currently.
        //Consider removal and using the passed string directly.
        this.roman = _roman;
        // perform lexical analysis
        try {
            translationSteps.add("Invoking lexical analyzer.");
            lexicalAnalyzer.analyze(roman);
            // create a parse tree
            translationSteps.add("Invoking parser.");
            parser.parse(lexicalAnalyzer.getTokens());
            // generate intermediate code
            translationSteps.add("Invoking IC generator.");
            intermediateCodeGenerator.generate(parser.getParseTree());
            // evaluate by executing the intermediate code
            translationSteps.add("Invoking evaluator.");
            arabic = evaluator.evaluate(intermediateCodeGenerator.getCode());
        } catch (RomanTranslationException e) {
            translationSteps.add(String.format("Error thrown from %s.", e.getAffectedModule()));
            throw e;
        } catch (Exception ex) {
            throw new RomanTranslationException(TAG, ex.getMessage());
        }
    }

    public HashMap<String,ArrayList<String>> getAllTranslationSteps()
    {
        //Will be empty on first call, but if called successively, don't waste time.
        if (allTranslationSteps.isEmpty())
            populateAllTranslationSteps();

        return allTranslationSteps;
    }

    private void populateAllTranslationSteps()
    {
        allTranslationSteps.put(TAG, translationSteps);
        if (lexicalAnalyzer != null)
            allTranslationSteps.put(LexicalAnalyzer.TAG, lexicalAnalyzer.getTranslationSteps());
        if (parser != null)
            allTranslationSteps.put(Parser.TAG, parser.getTranslationSteps());
        if (intermediateCodeGenerator != null)
            allTranslationSteps.put(IntermediateCodeGenerator.TAG, intermediateCodeGenerator.getTranslationSteps());
        if (evaluator != null)
            allTranslationSteps.put(Evaluator.TAG, evaluator.getTranslationSteps());
    }
}
