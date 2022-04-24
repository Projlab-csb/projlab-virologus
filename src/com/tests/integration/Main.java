package com.tests.integration;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> tests = getTests();
        for (String testPath : tests) {
            String testName = testPath.substring(testPath.lastIndexOf("/") + 1);
            System.out.println("-----------------------------------------------------");
            System.out.println("Running test: " + testName);

            System.out.println("Test finished");
            System.out.println("-----------------------------------------------------\n\n");
        }
    }

    public static boolean runTest(String testPath) {
        File inputFile = new File(testPath + "/input.txt");
        File expectedOutputFile = new File(testPath + "/expectedOutput.txt");

        IntegrationTest test = new IntegrationTest();
        String inputString = getFullFileContent(inputFile);
        String expectedOutputString = getFullFileContent(expectedOutputFile);

        test.runTest(inputString);
        return test.getOutput().equals(expectedOutputString);
    }

    /**
     * THIS METHOD MUST BE DELETED BEFORE SUBMISSION
     * //TODO: Delete this method
     * @param testPath
     */
    public static void writeTestOutputToFile(String testPath) {
        File inputFile = new File(testPath + "/input.txt");
        File expectedOutputFile = new File(testPath + "/expectedOutput.txt");

        IntegrationTest test = new IntegrationTest();
        String inputString = getFullFileContent(inputFile);

        test.runTest(inputString);
        String output = test.getOutput();
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
