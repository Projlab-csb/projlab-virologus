package com.csb.strategies;

import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;
import java.io.Serializable;

/**
 * The class, what describe the way the Virologist moves from one Field to another.
 *
 */
public class DefaultMove implements MoveStrategyInterface, Serializable {

    /**
     * Default move implementation of the Virologist.
     * @param virologist - the Virologist who moves
     */
    @Override
    public void move(Virologist virologist, int nextTileIndex) {
        //remove from the previous field
        virologist.getField().removeVirologist(virologist);

        //migrate to the next field
        virologist.getField().getNeighbors().get(nextTileIndex).acceptVirologist(virologist);
        System.out.println(virologist.getName() + " hasmoved to " + virologist.getField().getClass().getSimpleName());
    }
}
