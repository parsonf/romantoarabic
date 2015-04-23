package edu.compilers.teamb;


import java.util.*;

public class Parser {
    public static final String TAG = "Parser";
    private ArrayList<String> translationSteps = new ArrayList<>();
    private Stack<String> lexemes;
    private Node parseTree;
    private Hashtable<ParseTableCell, Production> parseTable;

    public Parser () {
        lexemes = new Stack<>();
        parseTree = new Node("S");
        parseTable = RomanToArabic.getParseTable();
    }

    public void parse(ArrayList<Token> tokens) throws RomanTranslationException {
        lexemes.push("$");
        Collections.reverse(tokens);
        for(Token t : tokens) {
            lexemes.push(t.getLexeme());
        }
        parse(parseTree);
        translationSteps.add("Parse tree: " + parseTree);
    }
    public void parse(Node n) throws RomanTranslationException {
        String lex = lexemes.peek();
        if (lex.equals("$")) {
            return; // we're done.
        }
        if (n.getValue().equals("ERROR")) {
            throw new RomanTranslationException(TAG, "parsing error");
        } else if(n.getValue().equals("")) {
            // empty string produced, do nothing
        } else if(n.getValue().equals(lex)) {
            // this is our terminal.
            lexemes.pop();
            // no further action needed. next.
        } else {
            // so we've got a non-terminal.
            Production production = parseTable.get(new ParseTableCell(n.getValue(), lex));
            String[] body = production.getBody().split("\\s");
            // add production as children to node.
            for (String aBody : body) {
                n.addChild(aBody);
            }
            // parse each node.
            for(Node child : n.getChildren()) {
                parse(child);
            }
        }
    }

    public Node getParseTree() { return parseTree; }
    public ArrayList<String> getTranslationSteps() {
        return translationSteps;
    }
}
