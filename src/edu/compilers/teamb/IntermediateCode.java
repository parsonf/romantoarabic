package edu.compilers.teamb;


public class IntermediateCode {
    public enum Action { ADD, SUBTRACT, MOVE }
    public enum Location { RESULTREG, REG1, REG2 }

    // results of mathematical operations go here by default.
    // a move action is needed to move it into a different register if desire to keep result.
    private Integer calcResultRegister;

    private Integer intRegister1;
    private Integer intRegister2;




    public void asdf() {
        ICStep ic = new ICStep(IntermediateCode.Action.ADD, 2, 3);
        int x2 = (int) ic.x2();
        if (ic.x2() instanceof Integer) {
            Integer x3 = ((Integer) ic.x2());
        }
    }
}
