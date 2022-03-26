package com.csb.skeletonTester.Tests;

import com.csb.agents.Agent;
import com.csb.agents.Paralyzed;
import com.csb.agents.Protection;
import com.csb.agents.VitusDance;
import com.csb.collectables.gencodes.Gencode;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

import java.util.ArrayList;

public class RefreshAgents extends Test {
    public String getName() {
        return "Refresh Agents";
    }
    public void runTest() {
        Virologist virologist = new Virologist();
        ArrayList<Agent> agents = new ArrayList<Agent>();
        agents.add(new Paralyzed());
        agents.add(new Protection());
        agents.add(new VitusDance());
        virologist.setAgentlist(agents);
        virologist.refreshAgents();
    }
}
