package edu.compilers.teamb;

public class OutputInterface{
    public static boolean isVerbose = false;
    public static final String ANSI_DEFAULT = "\u001B[0m";
    public static final String ANSI_VERBOSE = "\u001B[33m";

    public static void outputInfo(String _output) {
        System.out.println(_output);
    }
    public static void outputError(String _output) {
        System.err.println(_output);
    }
    public static void outputVerbose(String _tag, String _output) {
        if (isVerbose)
            System.out.println(String.format("%s[%s]: %s%s", ANSI_VERBOSE, _tag, _output, ANSI_DEFAULT));
    }
}

