package com.csb.agents;

import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

public class Forget extends Agent {
    /**
     * Apply the Forget effect on a virologist, removes an agent from the virologist's collection
     */
    public void applyEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setAgent();
        Tester.getInstance().functionEnd();
    }
    /**
     * This effect can't be removed, it does not have ttl, it is an empty function
     */
    @Override
    public void decreaseTTL(Virologist virologist) {
    }
    /**
     * This effect can't be removed, it is an empty function
     */
    @Override
    public void removeEffect(Virologist virologist) {
    }

}
