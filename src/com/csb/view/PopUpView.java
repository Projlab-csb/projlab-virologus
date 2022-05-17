package com.csb.view;

import javax.swing.*;

public class PopUpView {

    /**
     * Show an error message
     * @param message The message to show
     * @param title The title of the message
     */
    public static void showError(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Show a select option dialog
     * @param message The message to show
     * @param title The title of the message
     * @param options The options to choose from
     * @return The index of the selected option
     */
    public static int selectOption(String message, String title, String[] options) {
        return JOptionPane.showOptionDialog(
            null,
            message,
            title,
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );
    }

    /**
     * Show a select option dialog
     * @param message The message to show
     * @param title The title of the message
     * @param options The options to choose from
     * @return The selected item as a string
     */
    public static String selectOptionString(String message, String title, String[] options) {
        int id = selectOption(message, title, options);
        return options[id];
    }

    /**
     * Choose a number from a range
     * @param message The message to show
     * @param title The title of the message
     * @param min The minimum value
     * @param max The maximum value
     * @return The selected number
     */
    public static int numberInput(String message, String title, int min, int max) {
        while (true) {
            int input = Integer.parseInt(JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE));
            if (input >= min && input <= max) {
                return input;
            }
        }
    }

    /**
     * Shows a message
     * @param message The message to show
     */
    public static void showSuccess(String message) {
        JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
