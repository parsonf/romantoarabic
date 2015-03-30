package edu.compilers.teamb;

import java.util.ArrayList;

/**
 * LexicalAnalyzer.
 *
 * Input: String of characters not yet validated as valid Roman Numeral input.
 * Output: Either an ordered list of token objects or error, report, and quit process.
 *
 * Notes (will delete a lot of this later):
 * What will token objects look like?
 * We have no use for a symbol table, scratch that.
 * Obviously we need the terminal, and probably an attribute representing the value of that terminal.
 *
 * I = 1, V = 5, X = 10, L = 50, C = 100, D = 500, M = 1000
 *
 * Cannot have more than 3 of the same symbol in a row
 *
 * Algorithm for roman numerals: Stared at a chart of 1 through 100 and this seems to be the simplest algorithm
 * for how to parse this later on.  Each individual character is adding, UNLESS the very next character is of
 * higher rank than the one you're on, in which case it subtracts.  A few examples:
 *  XXIX = 29  X, 10, X, 20, I, add 1? next is X, so subtract, 19. then finally X, 29.
 *  XCIV = 94  X, next is higher rank, so -10. C. next is lower, so add 100=90. I, next is higher rank, so
 *  subtract 1, 89. last, V, no next, so add, 94.  This algorithm seems to work on all examples.
 *  So, each token it seems should be an individual character, since we can look ahead at the next token.
 *
 */
public class LexicalAnalyzer {
    private String input;
    private ArrayList<Token> tokens;

    public LexicalAnalyzer() {
        tokens = new ArrayList<>();
    }

    public void analyze(String input) {
        tokens.clear();
        this.input = input;

        // TODO : lexical analysis

    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }



}
