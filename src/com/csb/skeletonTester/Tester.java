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

    public static Tester getInstance(){
        if(_tester == null){
            _tester = new Tester();
        }
        return _tester;
    }

    public void functionStart(String functionName){
        System.out.println("\t".repeat(indentationCount) + "--> Function Start: " + functionName);
        indentationCount++;
    }

    public void functionEnd(String functionName){
        indentationCount--;
        System.out.println("\t".repeat(indentationCount) + "<-- Function End: " + functionName);
    }

    public List<TestInterface> getTestList() {
        return testList;
    }
}
