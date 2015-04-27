package edu.compilers.teamb;

import java.util.*;

import static edu.compilers.teamb.OutputInterface.testOutputFail;
import static edu.compilers.teamb.OutputInterface.testOutputPass;

/**
 * This class is designed to test the valid Roman to Arabic functionality of the program.
 */
public class TestValidConversion {
    private ArrayList<Integer> randomNumbers;
    private HashMap<String, Integer> romanNumbers;
    private Random random = new Random();
    private Integer iterations;

    /**
     * Used to test the conversion functionality of the program.
     * @param _iterations the amount of times to test.
     */
    public void testRandomConvert(Integer _iterations) {
        iterations = _iterations;
        setRandomNumbers();
        setRomanNumbers();
        runTest();
    }

    /**
     * Used to test the conversion of every number up to 3999 sequentially.
     * Only outputs errors.
     */
    public void testSequentialConvert()
    {
        ArabicToRoman a2r = new ArabicToRoman();
        boolean failure = false;
        for (Integer i = 1; i<=3999; i++)
        {
            String roman = a2r.getRoman(i);
            Integer result = translate(roman);
            if (!Objects.equals(result, i)) {
                outputFail(roman, result, i);
                failure = true;
            }
        }

        if (!failure)
            testOutputPass("All tests passed!");
    }

    /**
     * Set the list of random Roman Numerals to test.
     */
    private void setRomanNumbers() {
        romanNumbers = new HashMap<>();
        ArabicToRoman a2r = new ArabicToRoman();

        for (Integer num : randomNumbers){
            romanNumbers.put(a2r.getRoman(num), num);
        }
    }

    /**
     * Set the list of random numbers to test.
     */
    private void setRandomNumbers(){
        randomNumbers = new ArrayList<>();
        for (Integer i = 0; i<iterations; i++){
            randomNumbers.add(random.nextInt(3999)+1);
        }
    }

    /**
     * Iterate through the Roman Numerals and invoke the translator -
     * note: leave Verbose off unless you want a lot of spam
     */
    private void runTest(){
        Iterator romIter = romanNumbers.entrySet().iterator();
        Map.Entry<String, Integer> keyVal;

        while(romIter.hasNext())
        {
            keyVal = (Map.Entry) romIter.next();
            String roman;
            roman = keyVal.getKey();
            Integer expected = keyVal.getValue();
            Integer result = translate(roman);
            if (Objects.equals(result, keyVal.getValue()))
                outputPass(roman, result, expected);
            else
                outputFail(roman, result, expected);

        }
    }

    private Integer translate(String _roman)
    {
        Translator translator = new Translator();
        Integer ret = 0;
        try{
            translator.translate(_roman);
            ret = translator.getArabic();
        } catch (RomanTranslationException e) {
            testOutputFail(String.format("Failed to convert %s to Arabic.", _roman));
            OutputInterface.outputError(String.format("[%s]: %s.", e.getAffectedModule(), e.getMessage()));
        }

        return ret;
    }

    private void outputFail(String _input, Integer _output, Integer _expected){
        testOutputFail(String.format("[%s] converted to [%d] - expected [%d].", _input, _output, _expected));
    }

    private void outputPass(String _input, Integer _output, Integer _expected){
        testOutputPass(String.format("Converted [%s] to [%d] - expected [%d] - success!", _input, _output, _expected));
    }
}
