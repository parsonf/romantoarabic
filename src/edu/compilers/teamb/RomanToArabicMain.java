package edu.compilers.teamb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.PatternSyntaxException;

import static edu.compilers.teamb.OutputInterface.outputError;
import static edu.compilers.teamb.OutputInterface.outputInfo;

public class RomanToArabicMain implements Runnable {
    private String input;
    private Translator translator;
    public RomanToArabicMain() {
        input = "";
    }

    public void run() {
        startMessage();
        // main program loop
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            outputInfo(RomanToArabic.INPUT_PROMPT);
            try {
                input = br.readLine();
            } catch (IOException e) {
                displayError(e.getMessage());
                continue;
            }
            processCommand(input);
        } while (!(input.equalsIgnoreCase(RomanToArabic.CMD_EXIT)));
    }

    private void processCommand(String input) {
        String[] inputArray = input.split("\\s+");
        String argument = "";
        if (inputArray.length > 1) {
            argument = inputArray[1];
        }
        switch(inputArray[0].toLowerCase()) {
            case RomanToArabic.CMD_QUIET:
                OutputInterface.isVerbose = false;
                outputInfo("Output of translate set to quiet. Type 'verbose' to see verbose output.");
                break;
            case RomanToArabic.CMD_VERBOSE:
                OutputInterface.isVerbose = true;
                outputInfo("Output of translate set to verbose. Type 'quiet' to see result only.");
                break;
            case RomanToArabic.CMD_TRANSLATE:
                translateInput(argument);
                break;
            case RomanToArabic.CMD_EXIT:
                outputInfo("Exiting program...");
                break;
            default:
                displayError();
        }
    }

    private void printAcceptableCommands() {
        outputInfo("Here is a list of acceptable commands:");
        outputInfo(String.format("%-20s-  Converts Roman numerals to Arabic numerals.", String.format("%s [text]", RomanToArabic.CMD_TRANSLATE)));
        outputInfo(String.format("%-20s-  Sets the output of the translate command to display intermediate steps and the result.", RomanToArabic.CMD_VERBOSE));
        outputInfo(String.format("%-20s-  Sets the output of the translate command to only show the result.", RomanToArabic.CMD_QUIET));
        outputInfo(String.format("%-20s-  Exits the program.", RomanToArabic.CMD_EXIT));
    }

    private void startMessage() {
        outputInfo("Welcome!");
        printAcceptableCommands();
    }

    private void displayError(String _error)
    {
        outputError((!_error.isEmpty() ? _error : "Syntax error."));
        printAcceptableCommands();
    }

    private void displayError()
    {
        displayError("");
    }

    private void translateInput(String _argument)
    {
        if (_argument == null || _argument.isEmpty())
            outputInfo("Translate requires a second parameter.");
        else
        {
            try {
                if (!_argument.matches("(M|D|C|L|X|V|I)+"))
                    displayError("Translate requires the second character to be a Roman numeral.");
                else
                {
                    //re-instantiate to avoid cleanup
                    translator = new Translator();
                    outputInfo(String.format("Translating {%s} to Arabic numeral...", _argument));
                    translator.translate(_argument);
                    outputInfo(String.format("Result: %s", translator.getArabic()));
                }
            } catch (PatternSyntaxException ex)
            {
                displayError("Regex evaluation error.");
            } catch (RomanTranslationException e) {
                displayError(String.format("Error in %s module: %s", e.getAffectedModule(), e.getMessage()));
            }
        }
    }

    public static void main(String[] args) {
        (new Thread(new RomanToArabicMain())).start();
    }
}
