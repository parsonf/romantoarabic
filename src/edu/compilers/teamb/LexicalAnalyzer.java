package edu.compilers.teamb;

import java.util.ArrayList;
import java.util.Stack;

import static edu.compilers.teamb.OutputInterface.outputVerbose;

/**
 *
 */
public class LexicalAnalyzer {
    public static final String TAG = "Lexical Analyzer";
    private Stack<Character> stack;
    private ArrayList<Token> tokens;

    public LexicalAnalyzer() {
        tokens = new ArrayList<>();
        stack = new Stack<>();
    }

    public void analyze(String input) throws RomanTranslationException {
        tokens.clear();
        outputVerbose(TAG, "Converting input stack.");
        convertInputToStack(input);
        String analyzing = "";

        while (!stack.isEmpty()) {
            // read next token of input
            analyzing += stack.pop();
            outputVerbose(TAG, String.format("Analyzing {%s}", analyzing));
            for(String s : RomanToArabic.getValidTokens()) {
                if (analyzing.equals(s)) {
                    tokens.add(new Token(analyzing));
                    outputVerbose(TAG, String.format("Token found {%s}", analyzing));
                    analyzing = "";
                    break;
                }
            }
        }
        // the stack is empty...but did we identify everything??
        if (!analyzing.isEmpty()) {
            outputVerbose(TAG, String.format("Stack empty, but no token found for {%s}.", analyzing));
            throw new RomanTranslationException(TAG,"Could not identify a token for " + analyzing + ".  Problem is likely at '" + analyzing.charAt(0) + "'.");
        }
    }

    private void convertInputToStack(String s) {
        // start from the end, work towards the front
        // push it all onto the stack.
        StringBuilder sb = new StringBuilder(s).reverse();
        s = sb.toString();
        for( int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i));
            outputVerbose(TAG, String.format("Pushed {%s} onto stack.", s.charAt(i)));
        }
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }
}
