package edu.compilers.teamb;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Evaluator {
    private IntermediateCode ic;
    public static final String TAG = "Evaluator";
    private ArrayList<String> translationSteps = new ArrayList<>();
    private HashMap<String, Integer> registers;

    public Evaluator() {
        //ic = new IntermediateCode();
        registers = new HashMap<>();
    }

    public int evaluate(ArrayList<ICStep> _code) throws RomanTranslationException{
        int result;
        int currVal1;
        int currVal2;
        int currVal3;

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
                // if this var1 is not in the register, add it.
                if (!registers.containsKey(step.getAdd3().getName())) {
                    registers.put(step.getAdd3().getName(),step.getAdd3().getVal());
                }
                currVal3 = registers.get(step.getAdd3().getName());
            }

            switch(step.getAction()) {
                case ADD:
                    currVal1 = currVal2 + currVal3;
                    break;
                case SUBTRACT:
                    currVal1 = currVal2 - currVal3;
                    break;
                default:
                    //unknown
            }

            registers.put(step.getAdd1().getName(), currVal1);
        }
        result = registers.get("S");

        Iterator it = registers.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            translationSteps.add(pair.getKey() + " = " + Integer.toString((Integer)pair.getValue()));
        }

        return result;
    }

    public ArrayList<String> getTranslationSteps() { return translationSteps; }
}
