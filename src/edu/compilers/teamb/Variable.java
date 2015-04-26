package edu.compilers.teamb;

/**
 * Represents a variable.
 */
public class Variable {
    private String name;
    private int val;

    /**
     * Constructs a variable with a name and a value.
     *
     * @param _name the name of this variable.
     * @param _val the value of this variable.
     */
    public Variable(String _name, int _val) {
        this.name = _name;
        this.val = _val;
    }

    /**
     * Returns the name of this variable.
     *
     * @return the name of this variable.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the value of this variable.
     *
     * @return the value of this variable.
     */
    public int getVal() {
        return val;
    }
}
