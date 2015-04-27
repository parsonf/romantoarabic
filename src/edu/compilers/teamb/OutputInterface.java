package edu.compilers.teamb;

/**
 * Handles program output.
 */
public class OutputInterface{
    /**
     * Whether verbose mode is enabled or not.
     */
    public static boolean isVerbose = false;
    /**
     * Default ANSI terminal color - revert to this if the color is changed.
     */
    public static final String ANSI_DEFAULT = "\u001B[0m";
    /**
     * ANSI Yellow
     */
    public static final String ANSI_VERBOSE = "\u001B[33m";
    /**
     * ANSI Green
     */
    public static final String ANSI_PASS = "\u001B[32m";
    /**
     * ANSI Red
     */
    public static final String ANSI_FAIL = "\u001B[31m";

    /**
     * Outputs informational messages.
     *
     * @param _output the message to output.
     */
    public static void outputInfo(String _output) {
        System.out.println(_output);
    }

    /**
     * Outputs the RtA prompt
     */
    public static void outputPrompt() {
        System.out.print(RomanToArabic.INPUT_PROMPT);
    }
    /**
     * Outputs error messages.
     *
     * @param _output the error to output.
     */
    public static void outputError(String _output) {
        System.err.println(_output);
    }

    /**
     * Outputs verbose messages.
     *
     * @param _tag the Class the verbose message was generated from.
     * @param _output the verbose message to output.
     */
    public static void outputVerbose(String _tag, String _output) {
        if (isVerbose)
            System.out.println(String.format("%s[%s]: %s%s", ANSI_VERBOSE, _tag, _output, ANSI_DEFAULT));
    }

    /**
     * Outputs message for test pass.
     * @param _output the string to output.
     */
    public static void testOutputPass(String _output){
        System.out.println(String.format("%s%s%s", ANSI_PASS, _output, ANSI_DEFAULT));
    }

    /**
     * Outputs message for test fail.
     * @param _output the string to output.
     */
    public static void testOutputFail(String _output){
        System.out.println(String.format("%s%s%s", ANSI_FAIL, _output, ANSI_DEFAULT));
    }
}

