package com.csb.fields;

import com.csb.collectables.Collectable;
import com.csb.collectables.equipments.Equipment;
import com.csb.skeletonTester.Tester;
import java.util.ArrayList;


/**
 * The class of what describes a shelter.
 * From the shelter, Virologist can collect Equipments
 */
public class Shelter extends Field {

    private ArrayList<Collectable> collectables;

    /**
     * We can initialize a shelter with giving the list of its equipments
     * @param collectables - The equipments can be collected from a shelter
     */
    public Shelter(ArrayList<Collectable> collectables) {
        this.collectables = collectables;
    }

    public ArrayList<Collectable> getCollectable() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return collectables;
    }

}
