package edu.compilers.teamb;


import java.util.ArrayList;

public class Evaluator {
    public static final String TAG = "Evaluator";

    private ArrayList<String> translationSteps = new ArrayList<>();
    private ArrayList<ICStep> code;

    public Evaluator() {
        code = null;
    }

    public int evaluate(ArrayList<ICStep> code) throws RomanTranslationException{
        this.code = code;

        throw new RomanTranslationException(TAG, "Not implemented.");

        //return 0;
    }

    public ArrayList<String> getTranslationSteps() { return translationSteps; }
}
