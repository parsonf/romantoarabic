package edu.compilers.teamb;


import java.util.ArrayList;

public class Evaluator {
    private ArrayList<ICStep> code;

    public Evaluator() {
        code = null;
    }

    public int evaluate(ArrayList<ICStep> code) {
        this.code = code;

        return 0;
    }
}
