package com.csb.skeletonTester;

import java.util.ArrayList;
import java.util.List;

public class Tester {

    private static Tester _tester = null;
    private int indentationCount;

    private ArrayList<TestInterface> testList;

    public Tester() {
        //Init testList
        testList = new ArrayList<>();
        int indentationCount = 0;
    }

    public static Tester getInstance() {
        if (_tester == null) {
            _tester = new Tester();
        }
        return _tester;
    }

    public void functionStart() {
        System.out.println("\t".repeat(indentationCount) + "--> Function Start: " + getInvokeFunctionName());
        indentationCount++;
    }

    public void functionEnd() {
        indentationCount--;
        System.out.println("\t".repeat(indentationCount) + "<-- Function End: " + getInvokeFunctionName());
    }

    private String getInvokeFunctionName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String className = stackTrace[3].getClassName().substring(stackTrace[3].getClassName().lastIndexOf('.') + 1);
        return className + "." + stackTrace[3].getMethodName() + "()";
    }

    public List<TestInterface> getTestList() {
        return testList;
    }
}
