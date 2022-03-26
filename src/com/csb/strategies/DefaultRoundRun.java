package com.csb.strategies;

import com.csb.collectables.Collectable;
import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

/**
 * The class, what describe an average Round of a virologist, and the way it handles the robbing attempts
 *
 */
public class DefaultRoundRun implements RoundRunStrategyInterface{


    /**
     * The a Virologist react for a robbing attempt
     * By default a robbing attempt must fail
     * @param coll - The item wanted by the robber Virologist
     * @param targetVirologist - the robbed Virologist
     * @return
     */
    public Collectable handleSteal(Collectable coll,Virologist targetVirologist){
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return null;
    }

    @Override
    public void RoundRun() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
    }
}
