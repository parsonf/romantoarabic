package edu.compilers.teamb;


import java.util.ArrayList;
import java.util.HashMap;

import static edu.compilers.teamb.OutputInterface.outputVerbose;

/**
 * Receives intermediate code and evaluates it.
 * The intermediate code is presumed to be a series of addition or subtraction commands.
 */
public class Evaluator {
    public static final String TAG = "Evaluator";
    private HashMap<String, Integer> registers;

    /**
     * Default Constructor.
     */
    public Evaluator() {
        registers = new HashMap<>();
    }

    /**
     * When called, receives an ordered list of intermediate code and
     * returns the calculated result.
     *
     * @param _code the intermediate code to process.
     * @return the calculated result.
     */
    public int evaluate(ArrayList<ICStep> _code) {
        int result;
        int currVal1;
        int currVal2;
        int currVal3;

        outputVerbose(TAG, "Evaluating code...");
        for (ICStep step : _code) {

            // add1 is never a terminal, so no need to check for that.
            // if this var1 is not in the register, add it.
            if (!registers.containsKey(step.getAdd1().getName())) {
                registers.put(step.getAdd1().getName(),step.getAdd1().getVal());
            }
            currVal1 = registers.get(step.getAdd1().getName());

            if (step.getAdd2().getName().matches("M|D|C|L|X|V|I")) {
                // terminal. so take its value.
                currVal2 = step.getAdd2().getVal();
            } else { // rest are non-terminals.
                // if this var2 is not in the register, add it.
                if (!registers.containsKey(step.getAdd2().getName())) {
                    registers.put(step.getAdd2().getName(),step.getAdd2().getVal());
                }
                currVal2 = registers.get(step.getAdd2().getName());
            }

            if (step.getAdd3().getName().matches("M|D|C|L|X|V|I")) {
                // terminal. so take its value.
                currVal3 = step.getAdd3().getVal();
            } else { // rest are non-terminals.
                // if this var3 is not in the register, add it.
                if (!registers.containsKey(step.getAdd3().getName())) {
                    registers.put(step.getAdd3().getName(),step.getAdd3().getVal());
                }
                currVal3 = registers.get(step.getAdd3().getName());
            }

            switch(step.getAction()) {
                case ADD:
                    currVal1 = currVal2 + currVal3;
                    outputVerbose(TAG, String.format("Adding %d to %d: %d", currVal2, currVal3, currVal1));
                    break;
                case SUBTRACT:
                    currVal1 = currVal2 - currVal3;
                    outputVerbose(TAG, String.format("Subtracting %d from %d: %d", currVal2, currVal3, currVal1));
                    break;
                default:
                    //unknown
            }

            registers.put(step.getAdd1().getName(), currVal1);
        }
        result = registers.get("S");

        return result;
    }

    /**
     * The operations that can be performed by this class for each intermediate code instruction.
     */
    public enum Action { ADD, SUBTRACT }
}
