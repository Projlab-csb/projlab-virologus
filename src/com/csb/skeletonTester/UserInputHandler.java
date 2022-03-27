package com.csb.skeletonTester;

/**
 * This class is used to ask any required user input.
 */
public class UserInputHandler {

    /**
     * Ask the user for an Integer.
     * @param prompt The prompt to display to the user.
     * @return Integer: The user's input.
     * @throws NumberFormatException If the user's input is not an Integer.
     */
    public static int getUserInputInt(String prompt) throws NumberFormatException {
        System.out.print(prompt);
        return Integer.parseInt(System.console().readLine());
    }

    /**
     * Ask the user for a String.
     * @param prompt The prompt to display to the user.
     * @return String: The user's input.
     */
    public static String getUserInputString(String prompt) {
        System.out.print("[UserInput String] " + prompt);
        return System.console().readLine();
    }

    /**
     * Ask the user to select an option from a list of options.
     * @param prompt The prompt to display to the user.
     * @param options The available options.
     * @return String: The user's input, this is one of the options.
     */
    public static String getUserInputString(String prompt, String[] options) {
        System.out.print("[UserInput String] " + prompt + "(" + String.join(", ", options) + "): ");
        String userInput = System.console().readLine();
        while (!isValidOption(userInput, options)) {
            System.out.print("Invalid input. Please try again: ");
            userInput = System.console().readLine();
        }
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
        String userInput = System.console().readLine();
        while (!isValidOption(userInput, new String[] { "y", "n" })) {
            System.out.print("Invalid input. Please try again: ");
            userInput = System.console().readLine();
        }
        return userInput.equals("y");
    }
}
