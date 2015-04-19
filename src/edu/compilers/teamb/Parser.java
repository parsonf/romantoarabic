package edu.compilers.teamb;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public class Parser {
    private ArrayList<Token> tokens;
    private Node parseTree;
    private Hashtable<ParseTableCell, Production> parseTable;

    public Parser () {
        tokens = null;
        parseTree = new Node();
        parseTable = RomanToArabic.getParseTable();
    }

    public void parse(ArrayList<Token> t) {
        tokens = t;

        Stack<String> tokenizedInput;
    }

    public Node getParseTree() {
        return parseTree;
    }
}
