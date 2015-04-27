package edu.compilers.teamb;

/**
 * Represents a single instruction of intermediate code.
 */
public class ICStep {
    private Evaluator.Action action;
    private Variable add1;
    private Variable add2;
    private Variable add3;

    /**
     * Constructs an ICStep object.
     *
     * @param _add1 A Variable object that represents the first address.
     * @param _add2 A Variable object that represents the second address.
     * @param _add3 A variable object that represents the third address.
     * @param action The action to take.
     */
    public ICStep(Variable _add1, Variable _add2, Variable _add3, Evaluator.Action action) {
        this.action = action;
        this.add1 = _add1;
        this.add2 = _add2;
        this.add3 = _add3;
    }

    /**
     * Returns the action for this instruction.
     *
     * @return The action for this instruction.
     */
    public Evaluator.Action getAction() {
        return action;
    }

    /**
     * Returns the Variable for add1 of this instruction.
     *
     * @return the Variable for add1 of this instruction.
     */
    public Variable getAdd1() {
        return add1;
    }

    /**
     * Returns the Variable for add2 of this instruction.
     *
     * @return the Variable for add2 of this instruction.
     */
    public Variable getAdd3() {
        return add3;
    }

    /**
     * Returns the Variable for add3 of this instruction.
     *
     * @return the Variable for add3 of this instruction.
     */
    public Variable getAdd2() {
        return add2;
    }

    /**
     * Prints this instruction in a String-friendly way.
     *
     * @return the String representation of this object.
     */
    @Override
    public String toString() {
        String s = "";
        s += add1.getName() + ".val = ";
        s += add2.getName() + ".val + ";
        s += add3.getName() + ".val";

        return s;
    }
}
