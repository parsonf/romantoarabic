package edu.compilers.teamb;


import java.util.ArrayList;

public class IntermediateCodeGenerator {
    private Node parseTree;
    private ArrayList<ICStep> code;
    private IntermediateCode intermediateCode;

    public IntermediateCodeGenerator() {
        parseTree = null;
        code = null;
        intermediateCode = null;
    }

    public void generate(Node node) {
        this.parseTree = node;
    }

    public ArrayList<ICStep> getCode() {
        return code;
    }
}
