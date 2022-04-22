package com.csb.fields;

import com.csb.collectables.Collectable;
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
        return neighbors;
    }

    public Field getNeighbor(int direction) {
        return neighbors.get(direction);
    }

    public void addNeighbor(Field neighbor) {
        neighbors.add(neighbor);
    }

    public void acceptVirologist(Virologist virologist) {
        virologistonField.add(virologist);
    }

    public List<Virologist> getVirologists() {
        return virologistonField;
    }

    public void removeVirologist(Virologist virologist) {
        virologistonField.remove(virologist);
    }

    /**
     * @return the collectable items - there are no items
     */
    public ArrayList<Collectable> getCollectable() {
        return null;
    }

    public void removeCollectable(Collectable coll) {
        return;
    }
}
