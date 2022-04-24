package com.csb.agents;

import com.csb.fields.Field;
import com.csb.skeletonTester.Tester;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.strategies.MoveStrategyInterface;
import com.csb.utils.Random;
import com.csb.virologist.Virologist;
import java.util.List;

public class VitusDance extends Agent implements MoveStrategyInterface {

    /**
     * Apply the VitusDance effect on a virologist, now the virologist moves random directions
     * @param virologist the virologist to apply the effect on
     */
    public void applyEffect(Virologist virologist) {
        virologist.setMoveStrategy(this);
        virologist.storeAgent(this);
    }

    /**
     * Removes the VitusDance effect, now the virologist does not move random directions
     * @param virologist the virologist to remove the effect from
     */
    @Override
    public void removeEffect(Virologist virologist) {
        virologist.setMoveStrategy(this);
    }

    /**
     * This function handles the virologist's move(move random directions or not)
     * @param virologist the virologist to move
     */
    @Override
    public void move(Virologist virologist, int nextTileIndex) {
        //get a random tile to step
        Random r = new Random();
        int nextstep = r.randomBetween(0, virologist.getField().getNeighbors().size() - 1);

        //step to the given tile
        virologist.getField().removeVirologist(virologist);
        virologist.getField().getNeighbors().get(nextstep).acceptVirologist(virologist);

        System.out.println(virologist.getName() + " has moved to " + virologist.getField().getClass().getSimpleName());
    }
}
