package com.csb.fields;

import com.csb.collectables.Collectable;
import com.csb.skeletonTester.Tester;
import java.util.ArrayList;

/**
 * The class, what describes a laboratory.
 * From the lab, Virologist can collect gencodes to win the game
 */
public class Lab extends Field {

    private ArrayList<Collectable> gencodes;

    /**
     * We can initialize a lab with giving the list of the gencode(s) what are on the wall of the lab
     * @param gencodes - The genetical code(s) can be collected from a lab
     */
    public Lab(ArrayList<Collectable> gencodes) {
        this.gencodes = gencodes;
    }

    /**
     * @return the items (gencodes) what a Virologist can learn from the lab
     */
    public ArrayList<Collectable> getCollectable() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return gencodes;
    }
}
