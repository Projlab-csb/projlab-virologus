package com.csb.skeletonTester.Tests;

import com.csb.gameControl.GameController;
import com.csb.skeletonTester.Test;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.virologist.Virologist;

public class GameFlow extends Test {

    @Override
    public String getName() {
        return "GameFlow";
    }

    @Override
    public void runTest() {
        GameController gameController = new GameController();
        gameController.initGame();

        boolean isSomeoneWon = false;

        while (!isSomeoneWon) {
            for (Virologist virologist : gameController.getVirologists()) {
                virologist.startOfTurn();
                if (UserInputHandler.getUserInputBoolean("Did " + virologist + " won the game?")) {
                    isSomeoneWon = true;
                    break;
                }
            }
        }
    }
}
