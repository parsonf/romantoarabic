package edu.compilers.teamb;


import java.util.ArrayList;
import java.util.Dictionary;

public class Parser {
    private ArrayList<Token> tokens;
    private Node parseTree;
    private Dictionary<ParseTableCell, Production> parseTable;

    public Parser () {
        tokens = new ArrayList<>();
        parseTree = new Node();
        parseTable = RomanToArabic.getParseTable();
    }

    public void parse(ArrayList<Token> t) {
        tokens = t;
    }
}
