package edu.compilers.teamb;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import static edu.compilers.teamb.OutputInterface.*;

public class TestInvalidConversion {
    public static final String TAG = "Invalid Test";
    private ArrayList<String> badRoman;
    private Hashtable<ParseTableCell, Production> parseTable;
    private ArrayList<String> topLevelProds;
    private ArrayList<String> validExceptions;
    private String badRomanBuild;
    private Integer failedCount = 0;

    public TestInvalidConversion()
    {
        parseTable = RomanToArabic.getParseTable();
        badRoman = new ArrayList<>();

        validExceptions = new ArrayList<>();
        validExceptions.add("IV");
        validExceptions.add("IX");
        validExceptions.add("XL");
        validExceptions.add("CM");
        validExceptions.add("XC");
        validExceptions.add("CD");

        topLevelProds = new ArrayList<>();
        topLevelProds.add("ones");
        topLevelProds.add("tens");
        topLevelProds.add("hundreds");
        topLevelProds.add("thousands");
    }

    public void testInvalidConversion(){
        iterateParseTable();
        iterateBadRomans();
    }

    private void iterateBadRomans()
    {
        for(String bad : badRoman)
        {
            Translator translate = new Translator();
            try{
                translate.translate(bad);
                testOutputFail(String.format("[%s] passed translation.", bad));
            } catch (RomanTranslationException e) {
                failedCount++;
                outputVerbose(TAG, String.format("[%s] failed translation as expected.", bad));
            }
        }

        if (failedCount == badRoman.size())
            testOutputPass(String.format("%d bad combinations were tested and all failed.", failedCount));
        else
            testOutputFail(String.format("%d bad combinations were tested, but %d failed.", badRoman.size(), failedCount));
    }

    private void iterateParseTable(){
        ParseTableCell cell;
        Production prod;
        Iterator it = parseTable.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry<ParseTableCell, Production> kv = (Map.Entry) it.next();
            cell = kv.getKey();
            prod = kv.getValue();
            //only grab the productions that are errors
            if (!prod.getBody().equals(RomanToArabic.ERROR))
                continue;
            badRomanBuild = "";
            findInvalid(cell, prod);
        }
    }

    private void findInvalid(ParseTableCell _ptc, Production _prod)
    {
        //If the production has a nonterminal, add the current string to the list
        //If the production's nonterminal is top level, stop iterating that error
        badRomanBuild += _ptc.getLookahead();
        if (topLevelProds.contains(_prod.getBody()))
        {
            addToBadRomans();
            badRomanBuild = "";
            return;
        }

        if (!_prod.getBody().equals(RomanToArabic.ERROR)) {
            addToBadRomans();
        }

        //Search for an instance of a production that references the LMNT
        Iterator it = parseTable.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry<ParseTableCell, Production> kv = (Map.Entry) it.next();
            Production prod = kv.getValue();
            ParseTableCell ptc = kv.getKey();

            if (ptc.equals(_ptc))
                continue;

            String[] body = prod.getBody().split("\\s+");

            if (body.length <= 1)
                continue;
            else if (body.length == 2 && body[1].equals(_prod.getHead())){
                findInvalid(ptc, prod);
            }
        }
    }

    private void addToBadRomans()
    {
        String toAdd = new StringBuilder(badRomanBuild).reverse().toString();
        if (!validExceptions.contains(toAdd)) {
            badRoman.add(toAdd);
            outputVerbose(TAG, String.format("Added %s to list of bad Numbers.", toAdd));
        }
    }
}
