package com.csb.strategies;

import com.csb.collectables.Collectable;
import com.csb.fields.Field;
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
        return null;
    }

    /**
     * Handles the round of the virologist by managing the possible actions issued by the player who controls the virologist
     * @param virologist the one whose round is being handled
     */
    @Override
    public void RoundRun(Virologist virologist) {
        String input;
        int stepcounter = 0;
        do {
            input =
                UserInputHandler.getUserInputString(
                    "What to do?",
                    new String[] { "Move", "Collect", "Create Agent", "Use Agent", "Kill", "End Round" }
                );
            switch (input) {
                //if the player wants to move
                case "Move":
                    if (stepcounter == 0) {
                        int n = 0;
                        for (Field f : virologist.getField().getNeighbors()) {
                            System.out.println(f.getClass().toString() + "commad" + n);
                            n++;
                        }
                        virologist.move(
                            UserInputHandler.getUserInputInt("Where to move?", 0, virologist.getField().getNeighbors().size() - 1)
                        );
                        //System.out.println("Virologist has moved to ");
                        stepcounter++;
                    } else System.out.println("You cannot do more steps in this round");
                    break;
                //if the player wants to create a new agent for personal use
                case "Create Agent":
                    if (virologist.getGencodes().size() != 0) System.out.println("From what code do you want to create an agent"); else {
                        System.out.println("you don't have gencodes yet");
                    }
                    break;
                //if the player wants to attack someone with that agent

                case "Use Agent":
                    break;
                //if the player wants to collect something

                case "Collect":
                    virologist.collect(virologist.getField());

                    break;
                //if the player wants kill someone else

                case "Kill":
                    System.out.println("From what code do you want to create an agent");

                    break;
                default:
                    break;
            }
        } while (!input.equals("End Round"));
        virologist.emptyCreatedAgents();
    }
}
