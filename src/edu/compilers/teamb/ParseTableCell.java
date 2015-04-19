package edu.compilers.teamb;


public class ParseTableCell {
    private String leftMostNonTerminal;
    private String lookahead;

    public ParseTableCell(String leftMostNonTerminal, String lookahead) {
        this.leftMostNonTerminal = leftMostNonTerminal;
        this.lookahead = lookahead;
    }

    public String getLeftMostNonTerminal() {
        return leftMostNonTerminal;
    }

    public void setLeftMostNonTerminal(String leftMostNonTerminal) {
        this.leftMostNonTerminal = leftMostNonTerminal;
    }

    public String getLookahead() {
        return lookahead;
    }

    public void setLookahead(String lookahead) {
        this.lookahead = lookahead;
    }

    @Override
    public boolean equals(Object object) {
        boolean result = false;

        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            ParseTableCell ptc = (ParseTableCell) object;
            if (this.lookahead.equals(ptc.getLookahead())
                    && this.leftMostNonTerminal.equals(ptc.getLeftMostNonTerminal())) {
                result = true;
            }
        }

        return result;
    }

    @Override
    public int hashCode() {
        String s = lookahead + leftMostNonTerminal;

        return s.hashCode();
    }
}
