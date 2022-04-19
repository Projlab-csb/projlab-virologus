package com.csb;

import static com.csb.utils.ClassLoader.findAllClassesUsingClassLoader;

import com.csb.skeletonTester.TestInterface;
import com.csb.skeletonTester.Tester;
import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
        List<Class> classes = findAllClassesUsingClassLoader("com.csb.skeletonTester.Tests");
        classes.sort(Comparator.comparing(Class::getSimpleName));
        classes.forEach(clazz -> {
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
            int testCount = Tester.getInstance().getTestList().size();
            int maxUserInput = testCount;
            for (int i = 0; i < testCount; i++) {
                System.out.println(i + ":\t" + Tester.getInstance().getTestList().get(i).getName());
            }
            System.out.println("---------------------------");
            System.out.println(testCount + ": Run all tests\n");
            System.out.print("Enter a number between (-1 and " + maxUserInput + "): ");

            try {
                //Get user input
                userInput = Integer.parseInt(scanner.nextLine());
                if (userInput == -1) {
                    System.out.println("Exiting...");
                } else if (userInput == maxUserInput) {
                    //Run all tests
                    Tester.getInstance().getTestList().forEach(TestInterface::run);
                } else if (userInput >= 0 && userInput < testCount) {
                    //Test if the user input is in the valid range and if so run the test
                    Tester.getInstance().getTestList().get(userInput).run();
                } else {
                    System.out.println("Input is out of range, enter and integer between (-1 and " + maxUserInput + ")");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input (try to enter a number): " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
