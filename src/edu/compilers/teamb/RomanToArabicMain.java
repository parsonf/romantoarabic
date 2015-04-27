package edu.compilers.teamb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.PatternSyntaxException;

import static edu.compilers.teamb.OutputInterface.*;

/**
 * The command line interface for this program.
 */
public class RomanToArabicMain implements Runnable {
    private String input;
    private Translator translator;
    public RomanToArabicMain() {
        input = "";
    }

    /**
     * Starting point of the program.  Called by main() method after starting a new thread.
     */
    public void run() {
        startMessage();
        // main program loop
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            outputPrompt();
            try {
                input = br.readLine();
            } catch (IOException e) {
                displayError(e.getMessage());
                continue;
            }
            processCommand(input);
        } while (!(input.equalsIgnoreCase(RomanToArabic.CMD_EXIT)));
    }

    /**
     * Processes a command.
     *
     * @param input the command to process.
     */
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
            case RomanToArabic.CMD_TEST:
                runTest(inputArray);
                break;
            default:
                displayError();
        }
    }

    /**
     * Prints a list of acceptable commands.
     */
    private void printAcceptableCommands() {
        outputInfo("Here is a list of acceptable commands:");
        outputInfo(String.format("%-20s-  Converts Roman numerals to Arabic numerals.", String.format("%s [text]", RomanToArabic.CMD_TRANSLATE)));
        outputInfo(String.format("%-20s-  Sets the output of the translate command to display intermediate steps and the result.", RomanToArabic.CMD_VERBOSE));
        outputInfo(String.format("%-20s-  Sets the output of the translate command to only show the result.", RomanToArabic.CMD_QUIET));
        outputInfo(String.format("%-20s-  Exits the program.", RomanToArabic.CMD_EXIT));
    }

    /**
     * Prints the start message.
     */
    private void startMessage() {
        outputInfo("Welcome!");
        printAcceptableCommands();
    }

    /**
     * Outputs an error.
     *
     * @param _error The error to output.
     */
    private void displayError(String _error)
    {
        outputError((!_error.isEmpty() ? _error : "Syntax error."));
        printAcceptableCommands();
    }

    /**
     * A generic syntax error. Convenience method.
     */
    private void displayError()
    {
        displayError("");
    }

    /**
     * Handles processing of translating Roman to Arabic.
     *
     * @param _argument the presumably Roman text to translate.
     */
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
                    outputInfo(String.format("Result: %d", translator.getArabic()));
                }
            } catch (PatternSyntaxException ex)
            {
                displayError("Regex evaluation error.");
            } catch (RomanTranslationException e) {
                displayError(String.format("Error in %s module: %s", e.getAffectedModule(), e.getMessage()));
            }
        }
    }

    private void runTest(String[] _args)
    {
        String testType;

        switch(_args.length)
        {
            case 2:
            case 3:
                testType = _args[1];
                break;
            default:
                displayError();
                return;
        }

        switch(testType)
        {
            case RomanToArabic.PARM_TEST_RANDOM:
                if (_args.length <= 2) {
                    displayError();
                    return;
                }
                runRandomTest(_args[2]);
                break;
            case RomanToArabic.PARM_TEST_SEQUENTIAL:
                runSequentialTest();
                break;
            case RomanToArabic.PARM_TEST_INVALID:
                runInvalidTest();
                break;
            case RomanToArabic.CMD_VERBOSE:
                isTestVerbose = true;
                break;
            case RomanToArabic.CMD_QUIET:
                isTestVerbose = false;
                break;
            default:
                displayError();
                break;
        }
    }

    /**
     * Runs a test a specified number of times (for now, just valid translation).
     * @param _argument the amount of times to run the test.
     */
    private void runRandomTest(String _argument)
    {
        try{
            Integer iterations = Integer.parseInt(_argument);
            TestValidConversion test = new TestValidConversion();
            test.testRandomConvert(iterations);
        }
        catch (NumberFormatException e){
            outputError(String.format("Invalid number of iterations [%s].", _argument));
        }
    }

    private void runSequentialTest()
    {
        TestValidConversion test = new TestValidConversion();
        test.testSequentialConvert();
    }

    private void runInvalidTest()
    {
        TestInvalidConversion test = new TestInvalidConversion();
        test.testInvalidConversion();
    }

    /**
     * The main() method.
     *
     * @param args Arguments sent to the program (ignored)
     */
    public static void main(String[] args) {
        (new Thread(new RomanToArabicMain())).start();
    }
}
