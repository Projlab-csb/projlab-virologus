package com.csb.agents;

import com.csb.fields.Field;
import com.csb.skeletonTester.Tester;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.strategies.MoveStrategyInterface;
import com.csb.virologist.Virologist;
import java.util.List;

public class VitusDance extends Agent implements MoveStrategyInterface {

    /**
     * Apply the VitusDance effect on a virologist, now the virologist moves random directions
     */
    public void applyEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setMoveStrategy(this);
        Tester.getInstance().functionEnd();
    }

    /**
     * Decrease the time to live of the agent, when it reaches zero the agent will be removed
     */
    @Override
    public void decreaseTTL(Virologist virologist) {
        Tester.getInstance().functionStart();
        if (getTTL() == 0) {
            removeEffect(virologist);
        } else {
            setTTL(getTTL() - 1);
        }
        Tester.getInstance().functionEnd();
    }

    /**
     * Removes the VitusDance effect, now the virologist does not move random directions
     */
    @Override
    public void removeEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setMoveStrategy(this);
        Tester.getInstance().functionEnd();
    }

    /**
     * This function handles the virologist's move(move random directions or not)
     */
    @Override
    public void move(Virologist virologist, int nextTileIndex) {
        Tester.getInstance().functionStart();
        List<Field> neighbours = virologist.getField().getNeighbors();
        int randomFieldId = UserInputHandler.getUserInputInt(
            "What is the random generator output? (1-" + neighbours.size() + ")",
            1,
            neighbours.size()
        );
        virologist.setField(neighbours.get(randomFieldId - 1));
        Tester.getInstance().functionEnd();
    }
}
