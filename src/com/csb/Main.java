package com.csb;

import com.csb.skeletonTester.TestInterface;
import com.csb.skeletonTester.Tester;
import java.util.Scanner;

import static com.csb.utils.ClassLoader.findAllClassesUsingClassLoader;

public class Main {

    /**
     * This is the main method. It is the entry point of the program. It is the first method called when the program
     * is run. It is responsible for initializing the program and calling the run method. It is also responsible for calling
     * the test method. The test method is responsible for running the tests. The test method is called by the run method.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // User input scanner instance
        Scanner scanner = new Scanner(System.in);

        // Load all the test classes from skeletonTester.Tests package
        findAllClassesUsingClassLoader("com.csb.skeletonTester.Tests").forEach(clazz -> {
            try {
                Tester.getInstance().getTestList().add((TestInterface) clazz.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                System.out.println("Error loading test class");
                e.printStackTrace();
            }
        });

        while (true) {
            //Ask user which test they want to run
            System.out.println("Which test would you like to run?");
            //For all tests print an id, and the name of the test
            for (int i = 0; i < Tester.getInstance().getTestList().size(); i++) {
                System.out.println(i + ": " + Tester.getInstance().getTestList().get(i).getName());
            }
            System.out.print("Enter a number between (0 and " + (Tester.getInstance().getTestList().size() - 1) + "): ");

            //Get user input
            int userInput = -1;
            try {
                userInput = Integer.parseInt(scanner.nextLine());
                //Test if the user input is valid
                if (userInput >= 0 && userInput < Tester.getInstance().getTestList().size()) {
                    //Run the test
                    Tester.getInstance().getTestList().get(userInput).run();
                }else {
                    System.out.println("Invalid input");
                }
            }  catch (Exception e) {
                System.out.println("Invalid input (try to enter a number): " + e.getMessage());
            }
        }
    }
}
