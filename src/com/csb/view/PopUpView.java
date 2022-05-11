package com.csb.view;

import javax.swing.*;

public class PopUpView {

    public static void showError(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

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

    public static String selectOptionString(String message, String title, String[] options) {
        int id = selectOption(message, title, options);
        return options[id];
    }

    public static int numberInput(String message, String title) {
        return Integer.parseInt(JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE));
    }

    public static int numberInput(String message, String title, int min, int max) {
        while (true) {
            int input = Integer.parseInt(JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE));
            if (input >= min && input <= max) {
                return input;
            }
        }
    }
}
