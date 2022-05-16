package com.csb.skeletonTester.Tests;

import com.csb.gameControl.GameController;
import com.csb.skeletonTester.Test;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.virologist.Virologist;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Tests the game flow. For every virologist, the user is asked to enter the
 * if the virologist has won the game.
 * Until then the game continues, and calls each virologist's startOfTurn method.
 */
public class GameFlow extends Test {

    @Override
    public String getName() {
        return "GameFlow";
    }

    @Override
    public void runTest() {
        ArrayList<String> names = new ArrayList<>();
        names.add("John");
        names.add("Mary");
        GameController.getInstance().initGame(names);

        boolean isSomeoneWon = false;

        while (!isSomeoneWon) {
            for (Virologist virologist : GameController.getInstance().getAllVirologists()) {
                virologist.startOfTurn();
                if (UserInputHandler.getUserInputBoolean("Did " + virologist + " won the game?")) {
                    isSomeoneWon = true;
                    break;
                }
            }
        }
    }
}
