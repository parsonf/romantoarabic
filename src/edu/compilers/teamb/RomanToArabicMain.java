package edu.compilers.teamb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RomanToArabicMain implements Runnable {
    private String input;
    private boolean isVerbose;
    private Translator translator;

    public RomanToArabicMain() {
        input = "";
        isVerbose = false;
        translator = new Translator();
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
                e.printStackTrace();
                break;
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
                    System.out.println("Translating " + argument + " to Arabic numeral...");
                    translator.translate(argument);
                    System.out.println("Result: " + translator.getArabic());
                break;
            case "exit":
                    System.out.println("Exiting program...");
                break;
            default:
                System.out.println("Syntax error.");
                printAcceptableCommands();
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
        System.out.println("Here's a list of commands or something. Go.");
    }

    public static void main(String[] args) {
        (new Thread(new RomanToArabicMain())).start();
    }
}
