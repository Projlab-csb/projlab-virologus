package com.csb.fields;

import com.csb.collectables.Collectable;
import com.csb.collectables.equipments.Equipment;
import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;
import java.util.ArrayList;
import java.util.List;

/**
 * The class, what describes a shelter.
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

    /**
     * @return the items (Equipments) what are collecatble from the shelter
     */
    public ArrayList<Collectable> getCollectable() {
        Tester.getInstance().functionStart();
        ArrayList<Collectable> loot = new ArrayList<Collectable>();
        loot.add(collectables.get(0));
        collectables.remove(collectables.get(0));
        Tester.getInstance().functionEnd();
        return loot;
    }
}
