package com.csb.skeletonTester;

public class UserInputHandler {

    private static UserInputHandler _instance;

    private UserInputHandler() {}

    public static UserInputHandler getInstance() {
        if (_instance == null) {
            _instance = new UserInputHandler();
        }
        return _instance;
    }

    public int getUserInputInt(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(System.console().readLine());
    }

    public String getUserInputString(String prompt) {
        System.out.print("[UserInput String]" + prompt);
        return System.console().readLine();
    }

    public String getUserInputString(String prompt, String[] options) {
        System.out.print("[UserInput String]" + prompt + "(" + String.join(", ", options) + "): ");
        String userInput = System.console().readLine();
        while (!isValidOption(userInput, options)) {
            System.out.print("Invalid input. Please try again: ");
            userInput = System.console().readLine();
        }
        return userInput;
    }

    private boolean isValidOption(String userInput, String[] options) {
        for (String option : options) {
            if (option.equals(userInput)) {
                return true;
            }
        }
        return false;
    }

    public boolean getUserInputBoolean(String prompt) {
        System.out.print("[UserInput Boolean]" + prompt + " (y/n): ");
        String userInput = System.console().readLine();
        while (!isValidOption(userInput, new String[] { "y", "n" })) {
            System.out.print("Invalid input. Please try again: ");
            userInput = System.console().readLine();
        }
        return userInput.equals("y");
    }
}
