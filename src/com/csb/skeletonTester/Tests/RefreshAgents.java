package com.csb.skeletonTester.Tests;

import com.csb.collectables.gencodes.Gencode;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

public class RefreshAgents extends Test {
    public String getName() {
        return "Refresh Agents";
    }
    public void runTest() {
        Virologist virologist = new Virologist();
        virologist.refreshAgents();
    }
}
