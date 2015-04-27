package edu.compilers.teamb;

import java.util.*;

import static edu.compilers.teamb.OutputInterface.testOutputFail;
import static edu.compilers.teamb.OutputInterface.testOutputPass;

public class TestValidConversion {
    private ArrayList<Integer> randomNumbers;
    private HashMap<String, Integer> romanNumbers;
    private Random random = new Random();
    private Integer iterations;

    public void testConvert(Integer _iterations) {
        iterations = _iterations;
        setRandomNumbers();
        setRomanNumbers();
        runTest();
    }

    private void setRomanNumbers() {
        romanNumbers = new HashMap<>();
        ArabicToRoman a2r = new ArabicToRoman();

        for (Integer num : randomNumbers){
            romanNumbers.put(a2r.getRoman(num), num);
        }
    }

    private void setRandomNumbers(){
        randomNumbers = new ArrayList<>();
        for (Integer i = 0; i<iterations; i++){
            randomNumbers.add(random.nextInt(3999)+1);
        }
    }

    private void runTest(){
        Iterator romIter = romanNumbers.entrySet().iterator();
        Map.Entry<String, Integer> keyVal;

        while(romIter.hasNext())
        {
            keyVal = (Map.Entry) romIter.next();
            Translator translator = new Translator();
            String roman = "";
            try{
                roman = keyVal.getKey();
                Integer expected = keyVal.getValue();
                translator.translate(roman);
                Integer result = translator.getArabic();
                if (Objects.equals(result, keyVal.getValue()))
                    testOutputPass(String.format("Converted [%s] to [%d] successfully!", roman, result));
                else
                    testOutputFail(String.format("[%s] converted to [%d] - expected [%d].", roman, result, expected));
            } catch (RomanTranslationException e) {
                testOutputFail(String.format("Failed to convert %s to Arabic.", roman));
                OutputInterface.outputError(String.format("[%s]: %s.", e.getAffectedModule(), e.getMessage()));
            }
        }
    }
}
