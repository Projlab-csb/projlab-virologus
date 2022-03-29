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
     * @param virologist the virologist to apply the effect on
     */
    public void applyEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setMoveStrategy(this);
        Tester.getInstance().functionEnd();
    }

    /**
     * Removes the VitusDance effect, now the virologist does not move random directions
     * @param virologist the virologist to remove the effect from
     */
    @Override
    public void removeEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setMoveStrategy(this);
        Tester.getInstance().functionEnd();
    }

    /**
     * This function handles the virologist's move(move random directions or not)
     * @param virologist the virologist to move
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
