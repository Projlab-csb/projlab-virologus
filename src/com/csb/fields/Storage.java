package com.csb.fields;

import com.csb.collectables.Collectable;
import com.csb.skeletonTester.Tester;

import java.util.ArrayList;

public class Storage extends Field {
    private ArrayList<Collectable> collectables;

    public Storage(ArrayList<Collectable> collectables) {
        this.collectables = collectables;
    }

    public ArrayList<Collectable> getCollectable() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return collectables;
    }
}
