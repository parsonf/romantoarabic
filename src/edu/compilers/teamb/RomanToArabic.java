package edu.compilers.teamb;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

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

    public static Hashtable<ParseTableCell, Production> getParseTable() {

        Hashtable<ParseTableCell, Production> parseTable = new Hashtable<>();

        // row S
        parseTable.put(new ParseTableCell("S","I"), new Production("S","thousands hundreds tens ones"));
        parseTable.put(new ParseTableCell("S","V"), new Production("S","thousands hundreds tens ones"));
        parseTable.put(new ParseTableCell("S","X"), new Production("S","thousands hundreds tens ones"));
        parseTable.put(new ParseTableCell("S","L"), new Production("S","thousands hundreds tens ones"));
        parseTable.put(new ParseTableCell("S","C"), new Production("S","thousands hundreds tens ones"));
        parseTable.put(new ParseTableCell("S","D"), new Production("S","thousands hundreds tens ones"));
        parseTable.put(new ParseTableCell("S","M"), new Production("S","thousands hundreds tens ones"));
        parseTable.put(new ParseTableCell("S","$"), new Production("S",""));

        // row thousands
        parseTable.put(new ParseTableCell("thousands","I"), new Production("thousands",""));
        parseTable.put(new ParseTableCell("thousands","V"), new Production("thousands",""));
        parseTable.put(new ParseTableCell("thousands","X"), new Production("thousands",""));
        parseTable.put(new ParseTableCell("thousands","L"), new Production("thousands",""));
        parseTable.put(new ParseTableCell("thousands","C"), new Production("thousands",""));
        parseTable.put(new ParseTableCell("thousands","D"), new Production("thousands",""));
        parseTable.put(new ParseTableCell("thousands","M"), new Production("thousands","M t2", new int[]{1000, 0}));
        parseTable.put(new ParseTableCell("thousands","$"), new Production("thousands",""));

        // row t2
        parseTable.put(new ParseTableCell("t2","I"), new Production("t2",""));
        parseTable.put(new ParseTableCell("t2","V"), new Production("t2",""));
        parseTable.put(new ParseTableCell("t2","X"), new Production("t2",""));
        parseTable.put(new ParseTableCell("t2","L"), new Production("t2",""));
        parseTable.put(new ParseTableCell("t2","C"), new Production("t2",""));
        parseTable.put(new ParseTableCell("t2","D"), new Production("t2",""));
        parseTable.put(new ParseTableCell("t2","M"), new Production("t2","M", new int[]{1000}));
        parseTable.put(new ParseTableCell("t2","$"), new Production("t2",""));

        // row t3
        parseTable.put(new ParseTableCell("t3","I"), new Production("t3",""));
        parseTable.put(new ParseTableCell("t3","V"), new Production("t3",""));
        parseTable.put(new ParseTableCell("t3","X"), new Production("t3",""));
        parseTable.put(new ParseTableCell("t3","L"), new Production("t3",""));
        parseTable.put(new ParseTableCell("t3","C"), new Production("t3",""));
        parseTable.put(new ParseTableCell("t3","D"), new Production("t3",""));
        parseTable.put(new ParseTableCell("t3","M"), new Production("t3","M", new int[]{1000}));
        parseTable.put(new ParseTableCell("t3","$"), new Production("t3",""));

        // row hundreds
        parseTable.put(new ParseTableCell("hundreds","I"), new Production("hundreds",""));
        parseTable.put(new ParseTableCell("hundreds","V"), new Production("hundreds",""));
        parseTable.put(new ParseTableCell("hundreds","X"), new Production("hundreds",""));
        parseTable.put(new ParseTableCell("hundreds","L"), new Production("hundreds",""));
        parseTable.put(new ParseTableCell("hundreds","C"), new Production("hundreds","C h2", new int[]{100, 0}));
        parseTable.put(new ParseTableCell("hundreds","D"), new Production("hundreds","D h5", new int[]{500, 0}));
        parseTable.put(new ParseTableCell("hundreds","M"), new Production("hundreds","ERROR"));
        parseTable.put(new ParseTableCell("hundreds","$"), new Production("hundreds",""));

        // row h2
        parseTable.put(new ParseTableCell("h2","I"), new Production("h2",""));
        parseTable.put(new ParseTableCell("h2","V"), new Production("h2",""));
        parseTable.put(new ParseTableCell("h2","X"), new Production("h2",""));
        parseTable.put(new ParseTableCell("h2","L"), new Production("h2",""));
        parseTable.put(new ParseTableCell("h2","C"), new Production("h2","C h3", new int[]{100, 0}));
        parseTable.put(new ParseTableCell("h2","D"), new Production("h2","D", new int[]{300}));
        parseTable.put(new ParseTableCell("h2","M"), new Production("h2","M", new int[]{800}));
        parseTable.put(new ParseTableCell("h2","$"), new Production("h2",""));

        // row h3
        parseTable.put(new ParseTableCell("h3","I"), new Production("h3",""));
        parseTable.put(new ParseTableCell("h3","V"), new Production("h3",""));
        parseTable.put(new ParseTableCell("h3","X"), new Production("h3",""));
        parseTable.put(new ParseTableCell("h3","L"), new Production("h3",""));
        parseTable.put(new ParseTableCell("h3","C"), new Production("h3","C", new int[]{100}));
        parseTable.put(new ParseTableCell("h3","D"), new Production("h3","ERROR"));
        parseTable.put(new ParseTableCell("h3","M"), new Production("h3","ERROR"));
        parseTable.put(new ParseTableCell("h3","$"), new Production("h3",""));

        // row h5
        parseTable.put(new ParseTableCell("h5","I"), new Production("h5",""));
        parseTable.put(new ParseTableCell("h5","V"), new Production("h5",""));
        parseTable.put(new ParseTableCell("h5","X"), new Production("h5",""));
        parseTable.put(new ParseTableCell("h5","L"), new Production("h5",""));
        parseTable.put(new ParseTableCell("h5","C"), new Production("h5","C h6", new int[]{100,0}));
        parseTable.put(new ParseTableCell("h5","D"), new Production("h5","ERROR"));
        parseTable.put(new ParseTableCell("h5","M"), new Production("h5","ERROR"));
        parseTable.put(new ParseTableCell("h5","$"), new Production("h5",""));

        // row h6
        parseTable.put(new ParseTableCell("h6","I"), new Production("h6",""));
        parseTable.put(new ParseTableCell("h6","V"), new Production("h6",""));
        parseTable.put(new ParseTableCell("h6","X"), new Production("h6",""));
        parseTable.put(new ParseTableCell("h6","L"), new Production("h6",""));
        parseTable.put(new ParseTableCell("h6","C"), new Production("h6","C h3", new int[]{100, 0}));
        parseTable.put(new ParseTableCell("h6","D"), new Production("h6","ERROR"));
        parseTable.put(new ParseTableCell("h6","M"), new Production("h6","ERROR"));
        parseTable.put(new ParseTableCell("h6","$"), new Production("h6",""));

        // row tens
        parseTable.put(new ParseTableCell("tens","I"), new Production("tens",""));
        parseTable.put(new ParseTableCell("tens","V"), new Production("tens",""));
        parseTable.put(new ParseTableCell("tens","X"), new Production("tens","X g2", new int[]{10, 0}));
        parseTable.put(new ParseTableCell("tens","L"), new Production("tens","L g5", new int[]{50, 0}));
        parseTable.put(new ParseTableCell("tens","C"), new Production("tens","ERROR"));
        parseTable.put(new ParseTableCell("tens","D"), new Production("tens","ERROR"));
        parseTable.put(new ParseTableCell("tens","M"), new Production("tens","ERROR"));
        parseTable.put(new ParseTableCell("tens","$"), new Production("tens",""));

        // row g2
        parseTable.put(new ParseTableCell("g2","I"), new Production("g2",""));
        parseTable.put(new ParseTableCell("g2","V"), new Production("g2",""));
        parseTable.put(new ParseTableCell("g2","X"), new Production("g2","X g3", new int[]{10, 0}));
        parseTable.put(new ParseTableCell("g2","L"), new Production("g2","L", new int[]{30}));
        parseTable.put(new ParseTableCell("g2","C"), new Production("g2","C", new int[]{80}));
        parseTable.put(new ParseTableCell("g2","D"), new Production("g2","ERROR"));
        parseTable.put(new ParseTableCell("g2","M"), new Production("g2","ERROR"));
        parseTable.put(new ParseTableCell("g2","$"), new Production("g2",""));

        // row g3
        parseTable.put(new ParseTableCell("g3","I"), new Production("g3",""));
        parseTable.put(new ParseTableCell("g3","V"), new Production("g3",""));
        parseTable.put(new ParseTableCell("g3","X"), new Production("g3","X", new int[]{10}));
        parseTable.put(new ParseTableCell("g3","L"), new Production("g3","ERROR"));
        parseTable.put(new ParseTableCell("g3","C"), new Production("g3","ERROR"));
        parseTable.put(new ParseTableCell("g3","D"), new Production("g3","ERROR"));
        parseTable.put(new ParseTableCell("g3","M"), new Production("g3","ERROR"));
        parseTable.put(new ParseTableCell("g3","$"), new Production("g3",""));

        // row g5
        parseTable.put(new ParseTableCell("g5","I"), new Production("g5",""));
        parseTable.put(new ParseTableCell("g5","V"), new Production("g5",""));
        parseTable.put(new ParseTableCell("g5","X"), new Production("g5","X g6", new int[]{10, 0}));
        parseTable.put(new ParseTableCell("g5","L"), new Production("g5","ERROR"));
        parseTable.put(new ParseTableCell("g5","C"), new Production("g5","ERROR"));
        parseTable.put(new ParseTableCell("g5","D"), new Production("g5","ERROR"));
        parseTable.put(new ParseTableCell("g5","M"), new Production("g5","ERROR"));
        parseTable.put(new ParseTableCell("g5","$"), new Production("g5",""));

        // row g6
        parseTable.put(new ParseTableCell("g6","I"), new Production("g6",""));
        parseTable.put(new ParseTableCell("g6","V"), new Production("g6",""));
        parseTable.put(new ParseTableCell("g6","X"), new Production("g6","X g3", new int[]{10, 0}));
        parseTable.put(new ParseTableCell("g6","L"), new Production("g6","ERROR"));
        parseTable.put(new ParseTableCell("g6","C"), new Production("g6","ERROR"));
        parseTable.put(new ParseTableCell("g6","D"), new Production("g6","ERROR"));
        parseTable.put(new ParseTableCell("g6","M"), new Production("g6","ERROR"));
        parseTable.put(new ParseTableCell("g6","$"), new Production("g6",""));

        // row ones
        parseTable.put(new ParseTableCell("ones","I"), new Production("ones","I k2", new int[]{1, 0}));
        parseTable.put(new ParseTableCell("ones","V"), new Production("ones","V k5", new int[]{5, 0}));
        parseTable.put(new ParseTableCell("ones","X"), new Production("ones","ERROR"));
        parseTable.put(new ParseTableCell("ones","L"), new Production("ones","ERROR"));
        parseTable.put(new ParseTableCell("ones","C"), new Production("ones","ERROR"));
        parseTable.put(new ParseTableCell("ones","D"), new Production("ones","ERROR"));
        parseTable.put(new ParseTableCell("ones","M"), new Production("ones","ERROR"));
        parseTable.put(new ParseTableCell("ones","$"), new Production("ones",""));

        // row k2
        parseTable.put(new ParseTableCell("k2","I"), new Production("k2","I", new int[]{1}));
        parseTable.put(new ParseTableCell("k2","V"), new Production("k2","V", new int[]{3}));
        parseTable.put(new ParseTableCell("k2","X"), new Production("k2","X", new int[]{8}));
        parseTable.put(new ParseTableCell("k2","L"), new Production("k2","ERROR"));
        parseTable.put(new ParseTableCell("k2","C"), new Production("k2","ERROR"));
        parseTable.put(new ParseTableCell("k2","D"), new Production("k2","ERROR"));
        parseTable.put(new ParseTableCell("k2","M"), new Production("k2","ERROR"));
        parseTable.put(new ParseTableCell("k2","$"), new Production("k2",""));

        // row k3
        parseTable.put(new ParseTableCell("k3","I"), new Production("k3","I", new int[]{1}));
        parseTable.put(new ParseTableCell("k3","V"), new Production("k3","ERROR"));
        parseTable.put(new ParseTableCell("k3","X"), new Production("k3","ERROR"));
        parseTable.put(new ParseTableCell("k3","L"), new Production("k3","ERROR"));
        parseTable.put(new ParseTableCell("k3","C"), new Production("k3","ERROR"));
        parseTable.put(new ParseTableCell("k3","D"), new Production("k3","ERROR"));
        parseTable.put(new ParseTableCell("k3","M"), new Production("k3","ERROR"));
        parseTable.put(new ParseTableCell("k3","$"), new Production("k3",""));

        // row k5
        parseTable.put(new ParseTableCell("k5","I"), new Production("k5","I k6", new int[]{1, 0}));
        parseTable.put(new ParseTableCell("k5","V"), new Production("k5","ERROR"));
        parseTable.put(new ParseTableCell("k5","X"), new Production("k5","ERROR"));
        parseTable.put(new ParseTableCell("k5","L"), new Production("k5","ERROR"));
        parseTable.put(new ParseTableCell("k5","C"), new Production("k5","ERROR"));
        parseTable.put(new ParseTableCell("k5","D"), new Production("k5","ERROR"));
        parseTable.put(new ParseTableCell("k5","M"), new Production("k5","ERROR"));
        parseTable.put(new ParseTableCell("k5","$"), new Production("k5",""));

        // row k6
        parseTable.put(new ParseTableCell("k6","I"), new Production("k6","I k3", new int[]{1, 0}));
        parseTable.put(new ParseTableCell("k6","V"), new Production("k6","ERROR"));
        parseTable.put(new ParseTableCell("k6","X"), new Production("k6","ERROR"));
        parseTable.put(new ParseTableCell("k6","L"), new Production("k6","ERROR"));
        parseTable.put(new ParseTableCell("k6","C"), new Production("k6","ERROR"));
        parseTable.put(new ParseTableCell("k6","D"), new Production("k6","ERROR"));
        parseTable.put(new ParseTableCell("k6","M"), new Production("k6","ERROR"));
        parseTable.put(new ParseTableCell("k6","$"), new Production("k6",""));

        return parseTable;
    }
}
