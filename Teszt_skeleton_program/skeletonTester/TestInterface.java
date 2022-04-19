package com.csb.skeletonTester;

/**
 * Interface implemented by every skeleton test.
 */
public interface TestInterface {
    /**
     * Get the name of the test.
     * @return the name of the test.
     */
    String getName();

    /**
     * The test will be executed by this method.
     */
    void runTest();

    /**
     * This is wrapper method for the runTest method.
     */
    void run();
}
