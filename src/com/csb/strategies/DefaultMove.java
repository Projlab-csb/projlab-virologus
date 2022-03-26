package com.csb.strategies;


import com.csb.skeletonTester.Tester;

/**
 * The class, what describe the way the Virologist moves from one Field to another.
 *
 */
public class DefaultMove implements MoveStrategyInterface{

    /**
     * Default move
     */
    @Override
    public void move() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
    }
}
