package com.tests.integration;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    //Set this to true to hide the test program child process output
    static final boolean IS_MUTED = true;

    //Set this to true to automatically write the program's output to the expected output file
    //Note: This overwrites all the test output contents
    static final boolean WRITE_OUT_BEFORE_TEST = false; //TODO: DELETE THIS

    public static void main(String[] args) {
        List<String> tests = getTests();
        int passed = 0;
        int failed = 0;
        for (String testPath : tests) {
            String testName = testPath.substring(testPath.lastIndexOf("/") + 1);
            System.out.println("-----------------------------------------------------");
            System.out.println("Running test: [" + testName + "]");

            //TODO: DELETE THIS
            if (WRITE_OUT_BEFORE_TEST) {
                System.out.println("WRITING OUTPUT EXPECTED FILE");
                writeTestOutputToFile(testPath);
            }
            //END DELETE

            boolean success = runTest(testPath);
            if (success) {
                passed++;
            } else {
                failed++;
            }

            System.out.println("Test finished" + (success ? " SUCCESS" : " FAILED"));
        }
        System.out.println("\n-----------------------------------------------------");
        System.out.println("Tests finished. " + passed + " passed, " + failed + " failed.");
    }

    public static boolean runTest(String testPath) {
        File inputFile = new File(testPath + "/input.txt");
        File expectedOutputFile = new File(testPath + "/expectedOutput.txt");

        IntegrationTest test = new IntegrationTest(IS_MUTED);
        String inputString = getFullFileContent(inputFile);
        String expectedOutputString = getFullFileContent(expectedOutputFile);

        test.runTest(inputString);
        return test.getOutput().equals(expectedOutputString);
    }

    /**
     * THIS METHOD MUST BE DELETED BEFORE SUBMISSION
     * //TODO: DELETE THIS
     * @param testPath
     */
    public static void writeTestOutputToFile(String testPath) {
        File inputFile = new File(testPath + "/input.txt");
        File expectedOutputFile = new File(testPath + "/expectedOutput.txt");

        IntegrationTest test = new IntegrationTest(true);
        String inputString = getFullFileContent(inputFile);

        test.runTest(inputString);
        String output = test.getOutput();

        try {
            FileWriter fw = new FileWriter(expectedOutputFile);
            fw.write(output);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getTests() {
        //Read all directories in the tests folder
        Path path = Paths.get("data/integrationTests");
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
