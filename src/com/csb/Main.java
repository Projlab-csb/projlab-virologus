package com.csb;

import static com.csb.utils.ClassLoader.findAllClassesUsingClassLoader;

import com.csb.skeletonTester.TestInterface;
import com.csb.skeletonTester.Tester;
import java.util.Scanner;

public class Main {

    /**
     * This is the main method. It is the entry point of the program. It is the first method called when the program
     * is run. It handles the test selection and the test execution. First all the test classes are loaded using the
     * class loader. Then the user is asked to select a test to run. The test is then executed. This is done until the
     * user enters "-1" as the test id, which is the exit command.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // User input scanner instance
        Scanner scanner = new Scanner(System.in);

        // Load all the test classes from skeletonTester.Tests package
        findAllClassesUsingClassLoader("com.csb.skeletonTester.Tests")
            .forEach(clazz -> {
                try {
                    Tester.getInstance().getTestList().add((TestInterface) clazz.getDeclaredConstructor().newInstance());
                } catch (Exception e) {
                    System.out.println("Cannot initialize test class: " + clazz.getName());
                    e.printStackTrace();
                }
            });

        int userInput = -2;

        while (userInput != -1) {
            //Ask user which test they want to run
            System.out.println("Which test would you like to run?");

            //For all tests print an id, and the name of the test
            System.out.println("-1:\tExit");
            for (int i = 0; i < Tester.getInstance().getTestList().size(); i++) {
                System.out.println(i + ":\t" + Tester.getInstance().getTestList().get(i).getName());
            }
            System.out.print("Enter a number between (-1 and " + (Tester.getInstance().getTestList().size() - 1) + "): ");

            try {
                //Get user input
                userInput = Integer.parseInt(scanner.nextLine());
                if (userInput == -1) {
                    System.out.println("Exiting...");
                } else if (userInput >= 0 && userInput < Tester.getInstance().getTestList().size()) {
                    //Test if the user input is in the valid range and if so run the test
                    Tester.getInstance().getTestList().get(userInput).run();
                } else {
                    System.out.println(
                        "Input is out of range, enter and integer between (-1 and " + (Tester.getInstance().getTestList().size() - 1) + ")"
                    );
                }
            } catch (Exception e) {
                System.out.println("Invalid input (try to enter a number): " + e.getMessage());
            }
        }
    }
}
