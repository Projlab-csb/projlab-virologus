package com.csb.agents;

import com.csb.collectables.Collectable;
import com.csb.collectables.equipments.Equipment;
import com.csb.collectables.gencodes.Gencode;
import com.csb.collectables.matters.AminoAcid;
import com.csb.collectables.matters.NucleicAcid;
import com.csb.fields.Field;
import com.csb.gameControl.GameController;
import com.csb.skeletonTester.Tester;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.strategies.RoundRunStrategyInterface;
import com.csb.view.PopUpView;
import com.csb.virologist.Virologist;

public class Paralyzed extends Agent implements RoundRunStrategyInterface {

    /**
     * Apply the Paralyzed effect on a virologist, now the virologist cant do anything
     * @param virologist the virologist to apply the effect on
     */
    public void applyEffect(Virologist virologist) {
        virologist.setRoundRunStrategy(this);
        virologist.storeAgent(this);
    }

    /**
     * Remove the Paralyzed effect, now the virologist can do things
     * @param virologist the virologist to remove the effect from
     */
    @Override
    public void removeEffect(Virologist virologist) {
        virologist.removeRoundRunStrategy(this);
    }

    /**
     * Other virologist steal collectables from the paralyed virologist
     * @param coll the collectable to steal
     * @param targetVirologist the virologist that steals the collectable
     */
    @Override
    public Collectable handleSteal(Collectable coll, Virologist targetVirologist) {
        coll.discard(targetVirologist);
        return coll;
    }

    @Override
    public void roundRun() {
        // do nothing
        PopUpView.showError(
            "The virologist is paralyzed, you can do nothing to prevent your impending doom (at least for the time being)!",
            "Paralyzed"
        );
    }

    @Override
    public void move(Virologist virologist) {}

    @Override
    public void murder(Virologist virologist) {}

    @Override
    public void steal(Virologist virologist) {}

    @Override
    public void discard(Virologist virologist) {}

    @Override
    public void createAgent(Virologist virologist) {}

    @Override
    public void useAgent(Virologist virologist) {}

    @Override
    public void collect(Virologist virologist) {}
    /**
     * This function handles the virologist's round(can or can't do things)
     * @param virologist the virologist that is doing the round
     */
    /*@Override
    public void RoundRun(Virologist virologist) {
        System.out.println("The virologist is paralyzed, you can do nothing to prevent your impending doom (at least for the time being)!");
        String input;
        int stepcounter = 0;
        do {
            input =
                UserInputHandler.getUserInputString(
                    "What to do dear " + virologist.getName() + "?",
                    new String[] { "End Round", "Exit Game" }
                );
            switch (input) {
                case "Exit Game":
                    boolean shouldSaveGame = UserInputHandler.getUserInputBoolean("Do you want to save the game state?");
                    if (shouldSaveGame) {
                        String mapName = UserInputHandler.getUserInputString("Enter a name for the save: ");
                        GameController.getInstance().saveGame(mapName);
                    }
                    System.out.println("You have exited the game");
                    System.exit(0);
                    return;
                default:
                    break;
            }
        } while (!input.equals("End Round"));
        virologist.emptyCreatedAgents();
        //The virologist can't do anything, hence the function is empty
    }*/

}
