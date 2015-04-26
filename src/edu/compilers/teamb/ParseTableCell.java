package edu.compilers.teamb;

/**
 * Represents a cell in the parse table.
 */
public class ParseTableCell {
    private String leftMostNonTerminal;
    private String lookahead;

    /**
     * Constructs the cell object from the two identifiers that identify this cell
     *
     * @param leftMostNonTerminal the left-most non-terminal, the row that this cell is in
     * @param lookahead the lookahead, the column that this cell is in
     */
    public ParseTableCell(String leftMostNonTerminal, String lookahead) {
        this.leftMostNonTerminal = leftMostNonTerminal;
        this.lookahead = lookahead;
    }

    /**
     * Returns the left-most non-terminal, or row header.
     *
     * @return the left-most non-terminal, or row header.
     */
    public String getLeftMostNonTerminal() {
        return leftMostNonTerminal;
    }

    /**
     * Returns the lookahead, or column header.
     *
     * @return the lookahead, or column header.
     */
    public String getLookahead() {
        return lookahead;
    }

    /**
     * Necessary to override equals so that two objects of this class can be compared for equality.
     * Equality is based on the two properties of left-most non-terminal and the lookahead.
     *
     * @param object the object to compare to
     * @return whether the passed object is equal to this one.
     */
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

    /**
     * Necessary to override hashcode so that two objects of this class can be compared for equality.
     *
     * @return the hashcode for this object.
     */
    @Override
    public int hashCode() {
        String s = lookahead + leftMostNonTerminal;

        return s.hashCode();
    }
}
