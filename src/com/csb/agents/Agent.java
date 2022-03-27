package com.csb.agents;

import com.csb.virologist.Virologist;

/**
 * The agent abstract class.
 */
public abstract class Agent {

    private int TTL;

    public abstract void applyEffect(Virologist virologist);

    public abstract void decreaseTTL(Virologist virologist);

    public abstract void removeEffect(Virologist virologist);

    /**
     * The agent's TTL - Time To Live field
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
