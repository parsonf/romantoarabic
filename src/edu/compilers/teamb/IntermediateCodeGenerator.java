package edu.compilers.teamb;


import java.util.ArrayList;

import static edu.compilers.teamb.OutputInterface.outputVerbose;

public class IntermediateCodeGenerator {
    public static final String TAG = "IC Generator";
    private ArrayList<ICStep> code;
    private Node parseTree;
    private IntermediateCode intermediateCode;

    public IntermediateCodeGenerator() {
        parseTree = null;
        intermediateCode = null;
        code = new ArrayList<>();
    }

    public void generate(Node _parseTree) throws RomanTranslationException {
        this.parseTree = _parseTree;
        traverse(_parseTree);

        outputVerbose(TAG,"Intermediate code generated:");
        for (ICStep step : code) {
            outputVerbose(TAG, step.toString());
        }
    }

    private void traverse(Node node) {
        if (!node.getChildren().isEmpty()) {
            for (Node child : node.getChildren()) {
                traverse(child);
            }
        }
        if (node == parseTree) {
            return;
        }
        code.add(new ICStep(node.getParent().getVar(), node.getParent().getVar(), node.getVar(), IntermediateCode.Action.ADD));
    }

    public ArrayList<ICStep> getCode() {
        return code;
    }
}
