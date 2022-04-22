package com.csb.skeletonTester.Tests;

import com.csb.gameControl.GameController;
import com.csb.skeletonTester.Test;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.virologist.Virologist;

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
        GameController.getInstance().initGame();

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
