package edu.compilers.teamb;


import java.util.ArrayList;

public class IntermediateCodeGenerator {
    public static final String TAG = "IC Generator";
    private ArrayList<String> translationSteps = new ArrayList<>();
    private Node parseTree;
    private ArrayList<ICStep> code;
    private IntermediateCode intermediateCode;

    public IntermediateCodeGenerator() {
        parseTree = null;
        code = null;
        intermediateCode = null;
    }

    public void generate(Node node) throws RomanTranslationException {
        this.parseTree = node;
        throw new RomanTranslationException(TAG, "Not implemented.");
    }

    public ArrayList<ICStep> getCode() {
        return code;
    }
    public ArrayList<String> getTranslationSteps() { return translationSteps; }
}
