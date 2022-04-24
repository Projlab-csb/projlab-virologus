package com.csb.agents;

import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;
import java.io.Serializable;

/**
 * The agent abstract class.
 */
public abstract class Agent implements Serializable {

    private int TTL = 3;

    /**
     * Apply the agent's effect to the virologist
     * @param virologist the virologist to apply the effect to
     */
    public abstract void applyEffect(Virologist virologist);

    /**
     * Decrements the agent's TTL
     * @param virologist the virologist who has the agent
     */
    public void decreaseTTL(Virologist virologist) {
        this.TTL--;
        if (this.TTL <= 0) {
            removeEffect(virologist);
        }
    }

    /**
     * Remove the agent from the virologist
     * @param virologist the virologist whom we remove the agent from
     */
    public abstract void removeEffect(Virologist virologist);

    /**
     * The agent's TTL - Time To Live field
     * At each turn, the agent's TTL is decremented by 1, and when it reaches 0, the agent is removed from the virologist
     * @return the TTL
     */
    public int getTTL() {
        return TTL;
    }

    /**
     * Sets the agent's TTL - Time To Live field
     * @param TTL the TTL to set
     */
    public void setTTL(int TTL) {
        this.TTL = TTL;
    }
}
