package edu.compilers.teamb;


public class ICStep {
    private IntermediateCode.Action action;
    private Variable add1;
    private Variable add2;
    private Variable add3;

    public ICStep(Variable _add1, Variable _add2, Variable _add3, IntermediateCode.Action action) {
        this.action = action;
        this.add1 = _add1;
        this.add2 = _add2;
        this.add3 = _add3;
    }

    public IntermediateCode.Action action() {
        return action;
    }

    public Variable getAdd1() {
        return add1;
    }

    public void setAdd1(Variable add1) {
        this.add1 = add1;
    }

    public Variable getAdd3() {
        return add3;
    }

    public void setAdd3(Variable add3) {
        this.add3 = add3;
    }

    public Variable getAdd2() {
        return add2;
    }

    public void setAdd2(Variable add2) {
        this.add2 = add2;
    }

    @Override
    public String toString() {
        String s = "";
        s += add1.getName() + "." + Integer.toString(add1.getVal()) + " = ";
        s += add2.getName() + "." + Integer.toString(add2.getVal()) + " + ";
        s += add3.getName() + "." + Integer.toString(add3.getVal());

        return s;
    }
}
