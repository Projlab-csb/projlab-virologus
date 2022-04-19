package com.csb.skeletonTester.Tests;

import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

/**
 * c
 */
public class RoundFlow extends Test {

    @Override
    public String getName() {
        return "Round Flow";
    }

    @Override
    public void runTest() {
        Virologist virologist = new Virologist();

        virologist.startOfTurn();
    }
}
