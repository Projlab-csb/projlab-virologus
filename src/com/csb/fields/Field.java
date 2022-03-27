package com.csb.fields;

import com.csb.collectables.Collectable;
import com.csb.skeletonTester.Tester;
import java.util.ArrayList;

/**
 * The class, what describes an average field.
 * There are no collectable items on this specific type of field
 */
public class Field {

    /**
     * @return the collectable items - there are no items
     */
    public ArrayList<Collectable> getCollectable() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return null;
    }
}
