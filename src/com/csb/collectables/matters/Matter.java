package com.csb.collectables.matters;

import com.csb.collectables.Collectable;
import com.csb.skeletonTester.Tester;

/**
 * Abstact class, what describes the matter
 */
public abstract class Matter implements Collectable {

    private int amount;

    /**
     * Constructors with or without parameter
     * the amount -1 means infinite amount
     */
    public Matter() {

        Tester.getInstance().functionStart();
        amount = -1;
        Tester.getInstance().functionEnd();
    }

    public Matter(int n) {

        Tester.getInstance().functionStart();
        amount = n;
        Tester.getInstance().functionEnd();
    }

    /**
     * Getters and setters for the amount variable
     */
    public int getAmount() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return amount;

    }

    public void setAmount(int amount) {
        Tester.getInstance().functionStart();
        this.amount = amount;
        Tester.getInstance().functionEnd();
    }
}
