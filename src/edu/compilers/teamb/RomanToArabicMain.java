package edu.compilers.teamb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RomanToArabicMain implements Runnable {
    private String input;

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
                e.printStackTrace();
                break;
            }
            processCommand(input);
        } while (!(input.equalsIgnoreCase("exit")));
    }

    private void processCommand(String input) {

    }

    private void startMessage() {
        System.out.println("Welcome!");
        System.out.println("Here's a list of commands or something. Go.");
    }

    public static void main(String[] args) {
        (new Thread(new RomanToArabicMain())).start();
    }
}
