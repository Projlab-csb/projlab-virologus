package com.csb.skeletonTester;

/**
 * This is a baseLine test class for the skeleton.
 */
public class Test implements TestInterface {

    /**
     * Returns the name of the test.
     * @return the name of the test.
     */
    @Override
    public String getName() {
        return "";
    }

    /**
     * This will be implemented by the test.
     */
    @Override
    public void runTest() {}

    /**
     * This is just a wrapper for the runTest method. It is used to run the test, and print pretty output.
     */
    @Override
    public void run() {
        //At the start of every test turn on the logging system, just in case it was turned off.
        Tester.getInstance().turnOnLogging();
        System.out.println("\n------ [ " + getName() + " ] Test started ------");
        runTest();
        System.out.println("------ [ " + getName() + " ] Test finished ------\n");
    }
}
