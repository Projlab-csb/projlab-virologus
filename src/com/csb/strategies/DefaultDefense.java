package com.csb.strategies;

import com.csb.skeletonTester.Tester;

/**
 * The class, what describe the actions of the Virologist, when it is attacked.
 *
 */
public class DefaultDefense implements DefenseStrategyInterface{

    /**
     * Default defense
     */
    @Override
    public void defense() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
    }
}
