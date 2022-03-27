package com.csb.skeletonTester.Tests;

import com.csb.fields.Field;
import com.csb.skeletonTester.Test;
import com.csb.skeletonTester.Tester;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.virologist.Virologist;
import java.util.List;

/**
 * Tests the default move of a virologist.
 */
public class MoveDefault extends Test {

    @Override
    public String getName() {
        return "Move Default";
    }

    @Override
    public void runTest() {
        Virologist virologist = new Virologist();
        Tester.getInstance().turnOffLogging();
        virologist.setField(new Field());

        List<Field> neighbours = virologist.getField().getNeighbors();
        int fieldId = UserInputHandler.getUserInputInt("Move to which field? (1-" + neighbours.size() + ")", 1, neighbours.size());

        Tester.getInstance().turnOnLogging();

        virologist.move(fieldId - 1);
    }
}
