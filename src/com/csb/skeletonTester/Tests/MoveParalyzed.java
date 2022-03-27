package com.csb.skeletonTester.Tests;

import com.csb.fields.Field;
import com.csb.skeletonTester.Test;
import com.csb.skeletonTester.Tester;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.virologist.Virologist;
import java.util.List;

public class MoveParalyzed extends Test {

    @Override
    public String getName() {
        return "Move Paralyzed";
    }

    @Override
    public void runTest() {
        Virologist virologist = new Virologist();
        Tester.getInstance().turnOffLogging();
        virologist.setField(new Field());

        List<Field> neighbours = virologist.getField().getNeighbors();
        int randomFieldId = UserInputHandler.getUserInputInt(
            "What is the random generator output? (1-" + neighbours.size() + ")",
            1,
            neighbours.size()
        );

        Tester.getInstance().turnOnLogging();

        virologist.move(randomFieldId - 1);
    }
}
