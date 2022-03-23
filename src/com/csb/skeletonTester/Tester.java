package com.csb.skeletonTester;

public class Tester {
    private Tester _tester;
    private int indentationCount;

    public Tester() {
        int indentationCount = 0;
    }

    public Tester getInstance(){
        if(_tester == null){
            _tester = new Tester();
        }
        return _tester;
    }

    public void functionStart(String functionName){
        System.out.println("\t".repeat(indentationCount) + "--> Function Start: " + functionName);
        indentationCount++;
    }

    public void FunctionEnd(String functionName){
        System.out.println("\t".repeat(indentationCount) + "--> Function End: " + functionName);
        indentationCount--;
    }
}
