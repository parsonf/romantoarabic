package edu.compilers.teamb;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class is used to convert an Arabic number to a Roman Numeral.
 * It is instanced so the list doesn't have to be rebuilt in repeated successive calls.
 */
public class ArabicToRoman {
    private LinkedHashMap<String, Integer> rNumbers = new LinkedHashMap<>();

    /**
     * Constructor: populate the translation list with values
      */
    public ArabicToRoman()
    {
        rNumbers.put("M", 1000);
        rNumbers.put("CM", 900);
        rNumbers.put("D", 500);
        rNumbers.put("CD", 400);
        rNumbers.put("C", 100);
        rNumbers.put("XC", 90);
        rNumbers.put("L", 50);
        rNumbers.put("XL", 40);
        rNumbers.put("X", 10);
        rNumbers.put("IX", 9);
        rNumbers.put("V", 5);
        rNumbers.put("IV", 4);
        rNumbers.put("I", 1);
    }

    /**
     * Convert the given Integer to a String that represents a Roman Numeral.
     * @param _arabic - the number to convert.
     * @return - the String representing the Roman Numeral.
     */
    public String getRoman(Integer _arabic)
    {
        String ret = "";
        for (Map.Entry<String, Integer> rSymbol : rNumbers.entrySet()){
            Integer count = _arabic / rSymbol.getValue();

            for (Integer amt = 0; amt < count; amt++)
                ret += rSymbol.getKey();

            _arabic %= rSymbol.getValue();
        }

        return ret;
    }
}
