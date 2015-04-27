package edu.compilers.teamb;

import java.util.ArrayList;
import java.util.HashMap;

import static edu.compilers.teamb.OutputInterface.outputVerbose;

/**
 * Receives Roman text, translates it to Arabic.
 */
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

    /**
     * Default constructor.
     */
    public Translator() {
        this.roman = "";
        this.arabic = 0;
        lexicalAnalyzer = new LexicalAnalyzer();
        parser = new Parser();
        intermediateCodeGenerator = new IntermediateCodeGenerator();
        evaluator = new Evaluator();
    }

    /**
     * Returns the arabic.
     *
     * @return the arabic.
     */
    public Integer getArabic() {
        return arabic;
    }

    /**
     * Translates roman to arabic.
     *
     * @param _roman the roman to translate.
     * @throws RomanTranslationException Thrown when an exception with translation occurs.
     */
    public void translate(String _roman) throws RomanTranslationException {
        //Saved class variable for usage later - its only use is for translation, currently.
        //Consider removal and using the passed string directly.
        this.roman = _roman;
        // perform lexical analysis
        try {
            outputVerbose(TAG, "Invoking lexical analyzer.");
            lexicalAnalyzer.analyze(roman);
            // create a parse tree
            outputVerbose(TAG, "Invoking parser.");
            parser.parse(lexicalAnalyzer.getTokens());
            // generate intermediate code
            outputVerbose(TAG, "Invoking IC generator.");
            intermediateCodeGenerator.generate(parser.getParseTree());
            // evaluate by executing the intermediate code
            outputVerbose(TAG, "Invoking evaluator.");
            arabic = evaluator.evaluate(intermediateCodeGenerator.getCode());
        } catch (RomanTranslationException e) {
            outputVerbose(TAG, String.format("Error thrown from %s.", e.getAffectedModule()));
            throw e;
        } catch (Exception ex) {
            throw new RomanTranslationException(TAG, ex.getMessage());
        }
    }
}
