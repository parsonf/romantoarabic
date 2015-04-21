package edu.compilers.teamb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

public class RomanToArabicMain implements Runnable {
    private String input;
    private boolean isVerbose = false;
    private Translator translator;

    public RomanToArabicMain() {
        input = "";
    }

    public void run() {
        startMessage();
        // main program loop
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.print("RtA> ");
            try {
                input = br.readLine();
            } catch (IOException e) {
                displayError(e.getMessage());
                continue;
            }
            processCommand(input);
        } while (!(input.equalsIgnoreCase("exit")));
    }

    private void processCommand(String input) {
        String[] inputArray = input.split("\\s+");
        String argument = "";
        if (inputArray.length > 1) {
            argument = inputArray[1];
        }
        switch(inputArray[0].toLowerCase()) {
            case "quiet":
                isVerbose = false;
                System.out.println("Output of translate set to quiet. Type 'verbose' to see verbose output.");
                break;
            case "verbose":
                isVerbose = true;
                System.out.println("Output of translate set to verbose. Type 'quiet' to see result only.");
                break;
            case "translate":
                translateInput(argument);
                break;
            case "exit":
                System.out.println("Exiting program...");
                break;
            default:
                displayError();
        }
    }

    private void printAcceptableCommands() {
        System.out.println("Here is a list of acceptable commands:");
        System.out.println("translate [text]  -  Converts Roman numerals to Arabic numerals.");
        System.out.println("verbose           -  Sets the output of the translate command to display intermediate steps and the result.");
        System.out.println("quiet             -  Sets the output of the translate command to only show the result.");
        System.out.println("exit              -  Exits the program.");
    }

    private void startMessage() {
        System.out.println("Welcome!");
        printAcceptableCommands();
    }

    private void displayError(String _error)
    {
        System.err.println((!_error.isEmpty() ?  _error : "Syntax error."));
        printAcceptableCommands();
    }

    private void displayError()
    {
        displayError("");
    }

    private void translateInput(String _argument)
    {
        if (_argument == null || _argument.isEmpty())
            System.err.println("Translate requires a second parameter.");
        else
        {
            try {
                if (!_argument.matches("(M|D|C|L|X|V|I)+"))
                    displayError("Translate requires the second character to be a Roman numeral.");
                else
                {
                    //re-instantiate to avoid cleanup
                    translator = new Translator();
                    System.out.println(String.format("Translating {%s} to Arabic numeral...", _argument));
                    translator.translate(_argument);
                    outputVerbose();
                    System.out.println(String.format("Result: %s", translator.getArabic()));
                }
            } catch (PatternSyntaxException ex)
            {
                displayError("Regex evaluation error.");
            } catch (RomanTranslationException e) {
                outputVerbose();
                displayError(String.format("Error in %s module: %s", e.getAffectedModule(), e.getMessage()));
            }
        }
    }

    private void outputVerbose()
    {
        if (!isVerbose)
            return;
        /*
        There is a more efficient way of handling this.
        We could generate our own communication type class that acts as an interface between
        anything that uses it and this class.  For instance: BlockingQueue - that would require multithreading, though.
        This would eliminate the need for passing list references but would also eliminate any "replay" functionality.
        */
        HashMap<String,ArrayList<String>> translationSteps = translator.getAllTranslationSteps();

        for (Map.Entry<String, ArrayList<String>> o : translationSteps.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            ArrayList moduleSteps = (ArrayList) pair.getValue();
            for (Object moduleStep : moduleSteps) {
                System.out.println(String.format("%s: %s", pair.getKey().toString(), moduleStep.toString()));
            }
        }
    }

    public static void main(String[] args) {
        (new Thread(new RomanToArabicMain())).start();
    }
}
