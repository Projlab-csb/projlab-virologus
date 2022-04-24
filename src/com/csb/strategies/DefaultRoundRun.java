package com.csb.strategies;

import com.csb.agents.Agent;
import com.csb.collectables.Collectable;
import com.csb.collectables.equipments.Equipment;
import com.csb.collectables.gencodes.Gencode;
import com.csb.collectables.matters.AminoAcid;
import com.csb.collectables.matters.NucleicAcid;
import com.csb.fields.Field;
import com.csb.skeletonTester.Tester;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.virologist.Virologist;

/**
 * The class, what describe an average Round of a virologist, and the way it handles the robbing attempts
 */
public class DefaultRoundRun implements RoundRunStrategyInterface {

    /**
     * The Virologist react for a robbing attempt
     * By default a robbing attempt must fail
     * @param coll - The item wanted by the robber Virologist
     * @param targetVirologist - the robbed Virologist
     * @return null
     */
    public Collectable handleSteal(Collectable coll, Virologist targetVirologist) {
        return null;
    }

    /**
     * Handles the round of the virologist by managing the possible actions issued by the player who controls the virologist
     * @param virologist the one whose round is being handled
     */
    @Override
    public void RoundRun(Virologist virologist) {
        String input;
        int stepcounter = 0;
        do {
            input =
                UserInputHandler.getUserInputString(
                    "What to do?",
                    new String[] { "Move", "Collect", "Create Agent", "Use Agent", "Kill", "End Round" }
                );
            switch (input) {
                //if the player wants to move
                case "Move":
                    if (stepcounter == 0) {
                        int n = 0;
                        for (Field f : virologist.getField().getNeighbors()) {
                            System.out.println(f.getClass().toString() + "commad" + n);
                            n++;
                        }
                        virologist.move(
                            UserInputHandler.getUserInputInt("Where to move?", 0, virologist.getField().getNeighbors().size() - 1)
                        );
                        //System.out.println("Virologist has moved to ");
                        stepcounter++;
                    } else System.out.println("You cannot do more steps in this round");
                    break;
                //if the player wants to create a new agent for personal use
                case "Create Agent":
                    if (virologist.getGencodes().size() != 0) {
                        System.out.println("From what code do you want to create an agent?");
                        int n = 0;
                        for (Gencode g : virologist.getGencodes()) {
                            System.out.println(g.getAgent().getClass().toString() + "creater gencode commad" + n);
                            n++;
                        }
                        virologist.createAgent(virologist.getGencodes().get(n));
                    } else {
                        System.out.println("you don't have gencodes yet");
                    }
                    break;
                //if the player wants to attack someone with that agent

                case "Use Agent":
                    if (virologist.getField().getVirologists().size() == 0 || virologist.getCreatedAgents().size() == 0) System.out.println(
                        "You can't attack anyone now!"
                    ); else {
                        //select the agent
                        int a = 0;
                        for (Agent ag : virologist.getCreatedAgents()) {
                            System.out.println("Use " + ag.getClass().toString() + " agent: commad " + a);
                            a++;
                        }
                        int selectedAgent = UserInputHandler.getUserInputInt("Which gencode do you want to use?", 0, a - 1);

                        //select the virologist to be attacket
                        int v = 0;
                        for (Virologist vir : virologist.getField().getVirologists()) {
                            System.out.println("Use agent on " + vir.getName() + " :commad " + v);
                            v++;
                        }
                        int selectedvirologist = UserInputHandler.getUserInputInt("Who do you want to attack", 0, v - 1);

                        virologist.useAgent(
                            virologist.getCreatedAgents().get(selectedAgent),
                            virologist.getField().getVirologists().get(selectedvirologist)
                        );
                    }
                    break;
                //if the player wants to collect something

                case "Collect":
                    virologist.collect(virologist.getField());
                    break;
                //if the player wants kill someone else

                case "Kill":
                    if (virologist.getField().getVirologists().size() == 0) System.out.println("You can't kill anyone now!"); else {
                        int v = 0;
                        for (Virologist vir : virologist.getField().getVirologists()) {
                            System.out.println(vir.getName() + " commad " + v);
                            v++;
                        }
                        int selectedvirologist = UserInputHandler.getUserInputInt("Who do you want to attack", 0, v - 1);
                        if (virologist.equals(virologist.getField().getVirologists().get(selectedvirologist))) System.out.println(
                            "If you want to do Harakiri, you have to buy the Harkir DLC"
                        ); else virologist.murder(virologist.getField().getVirologists().get(selectedvirologist));
                    }
                    break;
                case "Steal":
                    Collectable coll = null;
                    int v = 0;
                    for (Virologist vir : virologist.getField().getVirologists()) {
                        System.out.println("Steal from " + vir.getName() + " :commad " + v);
                        v++;
                    }
                    int selectedvirologist = UserInputHandler.getUserInputInt("Who do you want to attack", 0, v - 1);
                    String in = UserInputHandler.getUserInputString("What to do want to steal?", new String[] { "Matter", "Equipment" });
                    if (in.equals("Matter")) {
                        if (
                            virologist.getField().getVirologists().get(selectedvirologist).getNucleicAcid() == 0 &&
                            virologist.getField().getVirologists().get(selectedvirologist).getAminoAcid() == 0
                        ) {
                            System.out.println("You cannot steal that kind of collectable");
                        } else {
                            in =
                                UserInputHandler.getUserInputString(
                                    "What to do want to steal?",
                                    new String[] { "AminoAcid", "NucleicAcid" }
                                );
                            if (in.equals("AminoAcid")) {
                                if (virologist.getField().getVirologists().get(selectedvirologist).getAminoAcid() == 0) System.out.println(
                                    "You cannot steal that kind of collectable"
                                ); else {
                                    virologist.steal(
                                        new AminoAcid(virologist.getField().getVirologists().get(selectedvirologist).getAminoAcid()),
                                        virologist.getField().getVirologists().get(selectedvirologist)
                                    );
                                }
                            } else {
                                if (
                                    virologist.getField().getVirologists().get(selectedvirologist).getNucleicAcid() == 0
                                ) System.out.println("You cannot steal that kind of collectable"); else {
                                    virologist.steal(
                                        new NucleicAcid(virologist.getField().getVirologists().get(selectedvirologist).getNucleicAcid()),
                                        virologist.getField().getVirologists().get(selectedvirologist)
                                    );
                                }
                            }
                        }
                    } else {
                        if (virologist.getField().getVirologists().get(selectedvirologist).getEquipments().size() == 0) System.out.println(
                            "You cannot steal that kind of collectable"
                        ); else {
                            int e = 0;
                            for (Equipment eq : virologist.getField().getVirologists().get(selectedvirologist).getEquipments()) {
                                System.out.println("Steal " + eq.getClass().toString() + " :commad " + e);
                                e++;
                            }
                            int selectedequipment = UserInputHandler.getUserInputInt("What do you want do steal?", 0, e - 1);
                            virologist.steal(
                                virologist.getField().getVirologists().get(selectedvirologist).getEquipments().get(selectedequipment),
                                virologist.getField().getVirologists().get(selectedvirologist)
                            );
                        }
                    }

                    break;
                default:
                    break;
            }
        } while (!input.equals("End Round"));
        virologist.emptyCreatedAgents();
    }
}
