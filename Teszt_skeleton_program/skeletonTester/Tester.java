package com.csb.skeletonTester;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to test the Skeleton model.
 */
public class Tester {

    //The tester instance, used to implement Singleton pattern.
    private static Tester _tester = null;
    //This keeps track of the number of function calls, to properly indent the output.
    private int indentationCount;
    //The list of the available tests.
    private ArrayList<TestInterface> testList;
    private boolean isLoggingOn;

    /**
     * Constructor for the Tester class.
     * Initializes the testList, and indentationCount properties.
     */
    public Tester() {
        //Init testList, and indentationCount.
        testList = new ArrayList<>();
        isLoggingOn = true;
        int indentationCount = 0;
    }

    /**
     * This method is used to get the tester instance, used to implement Singleton pattern.
     * @return the tester instance.
     */
    public static Tester getInstance() {
        if (_tester == null) {
            _tester = new Tester();
        }
        return _tester;
    }

    /**
     * This method is called at the end of a tested function.
     * Gets the invoking function name, and class from the stack trace, and prints it with the proper indentation.
     */
    public void functionStart() {
        if (isLoggingOn) {
            System.out.println("\t".repeat(indentationCount) + "--> Function Start: " + getInvokeFunctionName());
        }
        indentationCount++;
    }

    /**
     * This method is called at the end of a tested function.
     * Gets the invoking function name, and class from the stack trace, and prints it with the proper indentation.
     */
    public void functionEnd() {
        indentationCount--;
        if (isLoggingOn) {
            System.out.println("\t".repeat(indentationCount) + "<-- Function End: " + getInvokeFunctionName());
        }
    }

    /**
     * Determines the invoking function name, and class from the stack trace.
     * @return the invoking function name, and class as a combined string in the format: "class.functionName()"
     */
    private String getInvokeFunctionName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String className = stackTrace[3].getClassName().substring(stackTrace[3].getClassName().lastIndexOf('.') + 1);
        return className + "." + stackTrace[3].getMethodName() + "()";
    }

    /**
     * Getter for the testList property.
     * @return the testList
     */
    public List<TestInterface> getTestList() {
        return testList;
    }

    public void turnOffLogging() {
        this.isLoggingOn = false;
    }

    public void turnOnLogging() {
        this.isLoggingOn = true;
    }
}
