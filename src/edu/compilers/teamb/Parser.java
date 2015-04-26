package edu.compilers.teamb;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Stack;

import static edu.compilers.teamb.OutputInterface.outputVerbose;

public class Parser {
    //Tag to identify the module
    public static final String TAG = "Parser";
    //Stack of lexemes to process
    private Stack<String> lexemes;
    //The root node of the parse tree
    private Node parseTree;
    //The parse table
    private Hashtable<ParseTableCell, Production> parseTable;
    //The cumulative parsed characters - for error handling
    private String parsed = "";

    public Parser () {
        lexemes = new Stack<>();
        parseTree = new Node("S");
        parseTable = RomanToArabic.getParseTable();
    }

    public void parse(ArrayList<Token> tokens) throws RomanTranslationException {
        lexemes.push(RomanToArabic.END_OF_INPUT);
        Collections.reverse(tokens);
        outputVerbose(TAG, "Pushing lexemes onto stack.");
        for(Token t : tokens) {
            lexemes.push(t.getLexeme());
        }
        parse(parseTree);
        outputVerbose(TAG, "Parse tree: " + parseTree);
    }
    public void parse(Node n) throws RomanTranslationException {
        String lex = lexemes.peek();
        if (lex.equals(RomanToArabic.END_OF_INPUT)) {
            outputVerbose(TAG, "Done parsing.");
            return; // we're done.
        }
        if (n.getVar().getName().equals(RomanToArabic.ERROR)) {
            Integer errorPosition = parsed.length()+1;
            parseError();
            throw new RomanTranslationException(TAG, String.format("Parsing error at position %d: %s.", errorPosition, parsed));
        } else if(n.getVar().getName().equals("")) {
            // empty string produced, do nothing
        } else if(n.getVar().getName().equals(lex)) {
            // this is our terminal.
            lexemes.pop();
            parsed+=lex;
            // no further action needed. next.
        } else {
            // so we've got a non-terminal.
            Production production = parseTable.get(new ParseTableCell(n.getVar().getName(), lex));
            String[] body = production.getBody().split("\\s");
            outputVerbose(TAG, String.format("Retrieved production for %s -> %s.", production.getHead(), production.getBody()));
            // add production as children to node.
            int i = 0;
            for (String aBody : body) {
                if (production.getInitVals().length == body.length) {
                    n.addChild(aBody, production.getInitVals()[i]);
                } else {
                    n.addChild(aBody);
                }

                if (!aBody.isEmpty()) {
                    if (aBody != RomanToArabic.ERROR)
                        outputVerbose(TAG, String.format("Created node for %s.", aBody));
                    else
                        outputVerbose(TAG, String.format("Error found at %s for %s.", production.getHead(), lex));
                }

                i++;
            }
            // parse each node.
            for(Node child : n.getChildren()) {
                parse(child);
            }
        }
    }

    public Node getParseTree() { return parseTree; }

    private void parseError()
    {

        if (!lexemes.isEmpty())
        {
            parsed = String.format("%s[%s]", parsed, lexemes.pop());
            while (!lexemes.isEmpty()) {
                String lex = lexemes.pop();
                if (!lex.equals(RomanToArabic.END_OF_INPUT))
                    parsed += lex;
            }
        }
    }
}
