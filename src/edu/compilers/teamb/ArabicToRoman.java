package edu.compilers.teamb;
import java.util.LinkedHashMap;
import java.util.Map;

public class ArabicToRoman {
    private LinkedHashMap<String, Integer> rNumbers = new LinkedHashMap<>();

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
