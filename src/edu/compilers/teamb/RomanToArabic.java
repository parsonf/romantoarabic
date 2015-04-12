package edu.compilers.teamb;

import java.util.ArrayList;
import java.util.Dictionary;

public class RomanToArabic {

    public static ArrayList<String> getValidTokens() {
        ArrayList<String> validTokens = new ArrayList<>();
        validTokens.add("I");
        validTokens.add("V");
        validTokens.add("X");
        validTokens.add("L");
        validTokens.add("C");
        validTokens.add("D");
        validTokens.add("M");
        return validTokens;
    }

    public static Dictionary<ParseTableCell, Production> getParseTable() {
        return null;
    }
}
