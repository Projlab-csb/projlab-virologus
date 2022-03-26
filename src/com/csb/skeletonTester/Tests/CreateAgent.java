package com.csb.skeletonTester.Tests;

import com.csb.collectables.gencodes.Gencode;
import com.csb.skeletonTester.Test;
import com.csb.virologist.*;

public class CreateAgent extends Test {
    public String getName() {
        return "Create Agent";
    }
    public void runTest() {
        Virologist virologist = new Virologist();
        Gencode genCode = new Gencode();
        virologist.createAgent(genCode);
    }
}
