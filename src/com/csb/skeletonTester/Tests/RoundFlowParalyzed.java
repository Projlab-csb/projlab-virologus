package com.csb.skeletonTester.Tests;

import com.csb.agents.Paralyzed;
import com.csb.skeletonTester.Test;
import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

public class RoundFlowParalyzed extends Test {

    @Override
    public String getName() {
        return "Round Flow Paralyzed";
    }

    @Override
    public void runTest() {
        Tester.getInstance().turnOffLogging();
        Paralyzed paralyze = new Paralyzed();
        Virologist virologist = new Virologist();
        virologist.useAgent(paralyze, virologist);
        Tester.getInstance().turnOnLogging();

        virologist.startOfTurn();
    }
}
