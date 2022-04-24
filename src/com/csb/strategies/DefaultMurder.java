package com.csb.strategies;

import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

/**
 * The class, what describe the way the Virologist moves from one Field to another.
 *
 */
public class DefaultMurder implements MurderStrategyInterface {

    /**
     * Default move implementation of the Virologist.
     * @param murderervirologist - the Virologist who wants to kill
     * @param murderedvirologist - the Virologist to be killed
     */
    @Override
    public void murder(Virologist murderervirologist, Virologist murderedvirologist) {
        System.out.println(
            murderervirologist.getName() + " tried to kill " + murderedvirologist.getName() + " But he failed, hence he doesn't have an axe"
        );
        //nothing happens
    }
}
