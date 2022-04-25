package com.tests.integration;

import com.csb.skeletonTester.UserInputHandler;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import javax.rmi.ssl.SslRMIClientSocketFactory;

public class Main {

    //Set this to true to hide the test program child process output
    static final boolean IS_MUTED = false;

    public static void main(String[] args) {
        List<String> tests = getTests();

        int userInput = -2;

        while (userInput != -1) {
            //Ask user which test they want to run

            //For all tests print an id, and the name of the test
            System.out.println("-1:\tExit");
            int testCount = tests.size();
            int maxUserInput = testCount;
            for (int i = 0; i < testCount; i++) {
                String testName = tests.get(i).substring(tests.get(i).lastIndexOf("/") + 1);
                System.out.println(i + ":\t" + testName);
            }
            System.out.println("---------------------------");
            System.out.println(testCount + ": Run all tests\n");

            //Get user input
            userInput = UserInputHandler.getUserInputInt("Which test would you like to run?", -1, maxUserInput);
            if (userInput == -1) {
                System.out.println("Exiting...");
            } else if (userInput == maxUserInput) {
                //Run all tests
                int passed = 0;
                int failed = 0;
                for (String testPath : tests) {
                    if (runTest(testPath)) {
                        passed++;
                    } else {
                        failed++;
                    }
                }
                System.out.println("\n-----------------------------------------------------");
                System.out.println("Tests finished. " + passed + " passed, " + failed + " failed.");
                break;
            } else if (userInput >= 0 && userInput < testCount) {
                //Test if the user input is in the valid range and if so run the test
                runTest(tests.get(userInput));
            } else {
                System.out.println("Input is out of range, enter and integer between (-1 and " + maxUserInput + ")");
            }
        }
    }

    private static boolean runTest(String testPath) {
        String testName = testPath.substring(testPath.lastIndexOf("/") + 1);
        System.out.println("-----------------------------------------------------");
        System.out.println("Running test: [" + testName + "]");

        File inputFile = new File(testPath + "/input.txt");
        File expectedOutputFile = new File(testPath + "/expectedOutput.txt");

        IntegrationTest test = new IntegrationTest(IS_MUTED);
        String inputString = getFullFileContent(inputFile);
        String expectedOutputString = getFullFileContent(expectedOutputFile);

        //Run the test
        test.runTest(inputString);
        boolean success = test.getOutput().equals(expectedOutputString);

        System.out.println("Test finished" + (success ? " SUCCESS" : " FAILED"));
        return success;
    }

    public static List<String> getTests() {
        //Read all directories in the tests folder
        Path path = Paths.get("../data/integrationTests");
        List<String> tests = null;
        try {
            tests = Files.list(path).filter(Files::isDirectory).map(Path::toString).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tests;
    }

    public static String getFullFileContent(File file) {
        Scanner myReader = null;
        try {
            myReader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String fileContent = "";
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            fileContent += data + "\n";
        }
        myReader.close();
        return fileContent;
    }
}
