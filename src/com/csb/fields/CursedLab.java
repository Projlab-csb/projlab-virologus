package com.csb.fields;

import com.csb.agents.BearDance;
import com.csb.collectables.Collectable;
import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;
import java.util.ArrayList;

/**
 * The class, what describes a laboratory.
 * From the cursedlab, Virologist can collect gencodes to win the game, but it infect them with beardance virus
 */
public class CursedLab extends Lab {

    private ArrayList<Collectable> gencodes;

    /**
     * We can initialize a cursed lab with giving the list of the gencode(s) what are on the wall of the lab
     * @param gencodes - The genetical code(s) can be collected from a lab
     */
    public CursedLab(ArrayList<Collectable> gencodes) {
        super(gencodes);
    }

    /**
     * @return the items (gencodes) what a Virologist can learn from the lab
     */
    public ArrayList<Collectable> getCollectable() {
        return gencodes;
    }

    /**
     *A cursedlab attack a virologist when he steps on it
     * @param virologist
     */
    @Override
    public void acceptVirologist(Virologist virologist) {
        virologist.attack(new BearDance(), null);
        super.acceptVirologist(virologist);
    }

    @Override
    public FIELD_TYPE getType() {
        return FIELD_TYPE.LAB;
    }
}
