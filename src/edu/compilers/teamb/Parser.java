package edu.compilers.teamb;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public class Parser {
    public static final String TAG = "Parser";
    private ArrayList<String> translationSteps = new ArrayList<>();
    private ArrayList<Token> tokens;
    private Node parseTree;
    private Hashtable<ParseTableCell, Production> parseTable;

    public Parser () {
        tokens = null;
        parseTree = new Node();
        parseTable = RomanToArabic.getParseTable();
    }

    public void parse(ArrayList<Token> t) throws RomanTranslationException {
        tokens = t;

        Stack<String> tokenizedInput;
        throw new RomanTranslationException(TAG, "Not implemented.");
    }

    public Node getParseTree() { return parseTree; }
    public ArrayList<String> getTranslationSteps() {
        return translationSteps;
    }
}
