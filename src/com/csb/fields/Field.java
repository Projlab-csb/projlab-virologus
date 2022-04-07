package com.csb.fields;

import com.csb.collectables.Collectable;
import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;
import java.util.ArrayList;
import java.util.List;

/**
 * The class, what describes an average field.
 * There are no collectable items on this specific type of field
 */
public class Field {

    private List<Field> neighbors;
    private List<Virologist> virologistonField;

    public List<Field> getNeighbors() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        //TODO: This is only a demo implementation, REMOVE THIS
        return List.of(new Field[] { new Field(), new Field(), new Field() });
    }

    public Field getNeighbor(int direction) {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        //TODO: This is only a demo implementation, REMOVE THIS
        return new Field();
    }

    public void addNeighbor(Field neighbor) {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
    }

    public void acceptVirologist(Virologist virologist) {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
    }

    public void removeVirologist(Virologist virologist) {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
    }

    /**
     * @return the collectable items - there are no items
     */
    public ArrayList<Collectable> getCollectable() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return null;
    }
}
