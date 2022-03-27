package com.csb.strategies;

import com.csb.collectables.Collectable;
import com.csb.skeletonTester.Tester;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.virologist.Virologist;

/**
 * The class, what describe an average Round of a virologist, and the way it handles the robbing attempts
 */
public class DefaultRoundRun implements RoundRunStrategyInterface {

    /**
     * The Virologist react for a robbing attempt
     * By default a robbing attempt must fail
     * @param coll - The item wanted by the robber Virologist
     * @param targetVirologist - the robbed Virologist
     * @return null
     */
    public Collectable handleSteal(Collectable coll, Virologist targetVirologist) {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return null;
    }

    /**
     * Handles the round of the virologist by managing the possible actions issued by the player who controls the virologist
     * @param virologist the one whose round is being handled
     */
    @Override
    public void RoundRun(Virologist virologist) {
        Tester.getInstance().functionStart();

        String input;
        do {
            input = UserInputHandler.getUserInputString("What to do?", new String[] { "Move", "Create Agent", "Use Agent", "End Round" });
            switch (input) {
                case "Move":
                    virologist.move(UserInputHandler.getUserInputInt("Where to move?"));
                    break;
                case "Create Agent":
                    System.out.println("Creating agent...(see specific test)");
                    break;
                case "Use Agent":
                    System.out.println("Using Agent...(see specific test)");
                    break;
                default:
                    break;
            }
        } while (!input.equals("End Round"));

        Tester.getInstance().functionEnd();
    }
}
