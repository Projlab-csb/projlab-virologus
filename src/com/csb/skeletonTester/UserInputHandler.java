package com.csb.skeletonTester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class is used to ask any required user input.
 */
public class UserInputHandler {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Ask the user for an Integer.
     * @param prompt The prompt to display to the user.
     * @return Integer: The user's input.
     * @throws NumberFormatException If the user's input is not an Integer.
     */
    public static int getUserInputInt(String prompt) {
        while (true) {
            System.out.print("[UserInput Int] " + prompt + ": ");
            try {
                int number = Integer.parseInt(br.readLine());
                System.out.println(number);
                return number;
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public static int getUserInputInt(String prompt, int min, int max) {
        while (true) {
            System.out.print("[UserInput Int ( " + min + " - " + max + " )]" + prompt + ": ");
            int input = 0;
            try {
                input = Integer.parseInt(br.readLine());
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
            }
            if (input >= min && input <= max) {
                System.out.println(input);
                return input;
            }
        }
    }

    /**
     * Ask the user for a String.
     * @param prompt The prompt to display to the user.
     * @return String: The user's input.
     */
    public static String getUserInputString(String prompt) {
        System.out.print("[UserInput String] " + prompt);
        try {
            String input = br.readLine();
            System.out.println(input);
            return input;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        return "";
    }

    /**
     * Ask the user to select an option from a list of options.
     * @param prompt The prompt to display to the user.
     * @param options The available options.
     * @return String: The user's input, this is one of the options.
     */
    public static String getUserInputString(String prompt, String[] options) {
        System.out.print("[UserInput String] " + prompt + "(" + String.join(", ", options) + "): ");
        String userInput = null;
        try {
            userInput = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (!isValidOption(userInput, options)) {
            System.out.print("Invalid input. Please try again: ");
            try {
                userInput = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(userInput);
        return userInput;
    }

    /**
     * Check if the user's input is one of the options.
     * @param userInput The user's input.
     * @param options The available options.
     * @return boolean: True if the user's input is one of the options, false otherwise.
     */
    private static boolean isValidOption(String userInput, String[] options) {
        for (String option : options) {
            if (option.equals(userInput)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Ask the user for a Boolean.
     * @param prompt The prompt to display to the user.
     * @return boolean: The user's input.
     */
    public static boolean getUserInputBoolean(String prompt) {
        System.out.print("[UserInput Boolean] " + prompt + " (y/n): ");
        String userInput = null;
        try {
            userInput = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (!isValidOption(userInput, new String[] { "y", "n" })) {
            System.out.print("Invalid input. Please try again: ");
            try {
                userInput = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(userInput);
        return userInput.equals("y");
    }
}
