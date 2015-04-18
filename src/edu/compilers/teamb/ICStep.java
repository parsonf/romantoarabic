package edu.compilers.teamb;


public class ICStep {
    private IntermediateCode.Action action;
    private Object x1;
    private Object x2;

    public ICStep(IntermediateCode.Action action, Object x1, Object x2) {
        this.action = action;
        this.x1 = x1;
        this.x2 = x2;
    }

    public IntermediateCode.Action action() {
        return action;
    }

    public Object x1() {
        return x1;
    }

    public Object x2() {
        return x2;
    }
}
