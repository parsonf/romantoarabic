package edu.compilers.teamb;


import java.util.ArrayList;

import static edu.compilers.teamb.OutputInterface.outputVerbose;

/**
 * Generates an ordered list of intermediate code from a parse tree.
 */
public class IntermediateCodeGenerator {
    public static final String TAG = "IC Generator";
    private ArrayList<ICStep> code;
    private Node parseTree;

    /**
     * Default constructor.
     */
    public IntermediateCodeGenerator() {
        parseTree = null;
        code = new ArrayList<>();
    }

    /**
     * The method that starts the process of generating intermediate code.
     *
     * @param _parseTree the parse tree to create intermediate code from.
     */
    public void generate(Node _parseTree) {
        this.parseTree = _parseTree;
        traverse(_parseTree);

        outputVerbose(TAG, "Intermediate code generated:");
        for (ICStep step : code) {
            outputVerbose(TAG, step.toString());
        }
    }

    /**
     * Recursive function that traverses a node and it's children, creating intermediate code along the way.
     *
     * @param node The node to traverse.
     */
    private void traverse(Node node) {
        if (!node.getChildren().isEmpty()) {
            for (Node child : node.getChildren()) {
                traverse(child);
            }
        }
        if (node == parseTree) {
            return;
        }
        code.add(new ICStep(node.getParent().getVar(), node.getParent().getVar(), node.getVar(), Evaluator.Action.ADD));
    }

    /**
     * Returns the intermediate code generated.
     *
     * @return the intermediate code generated.
     */
    public ArrayList<ICStep> getCode() {
        return code;
    }
}
