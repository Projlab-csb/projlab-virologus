package com.csb.skeletonTester.Tests;

import com.csb.fields.Field;
import com.csb.fields.Lab;
import com.csb.fields.Shelter;
import com.csb.fields.Storage;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

/**
 * Tests the initialization of the game. For each required element,
 * it creates that element
 */
public class InitGame extends Test {

    @Override
    public String getName() {
        return "Init Game";
    }

    @Override
    public void runTest() {
        Field field = new Field();
        Field lab = new Lab(null);
        Field shelter = new Shelter(null);
        Field storage = new Storage(null);

        field.addNeighbor(lab);
        lab.addNeighbor(shelter);
        shelter.addNeighbor(storage);
        storage.addNeighbor(field);

        Virologist virologist = new Virologist("bob");
        Virologist virologist2 = new Virologist("james");

        virologist.setField(field);
        virologist2.setField(shelter);
    }
}
