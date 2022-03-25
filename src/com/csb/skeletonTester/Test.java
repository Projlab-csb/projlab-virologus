package com.csb.skeletonTester;

public class Test implements TestInterface {

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void runTest() {}

    @Override
    public void run() {
        System.out.println("\n------ [ " + getName() + " ] Test started ------");
        runTest();
        System.out.println("------ [ " + getName() + " ] Test finished ------\n");
    }
}
