package com.csb.strategies;

import com.csb.agents.Agent;
import com.csb.collectables.Collectable;
import com.csb.collectables.equipments.Equipment;
import com.csb.collectables.gencodes.Gencode;
import com.csb.collectables.matters.AminoAcid;
import com.csb.collectables.matters.NucleicAcid;
import com.csb.fields.Field;
import com.csb.fields.Shelter;
import com.csb.gameControl.GameController;
import com.csb.skeletonTester.Tester;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.view.PopUpView;
import com.csb.virologist.Virologist;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class, what describe an average Round of a virologist, and the way it handles the robbing attempts
 */
public class DefaultRoundRun implements RoundRunStrategyInterface, Serializable {

    int stepcounter = 0;

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

    @Override
    public void roundRun() {
        stepcounter = 0;
    }

    @Override
    public void move(Virologist virologist) {
        if (stepcounter == 0) {
            final int[] id = { -1 };
            String[] options = virologist
                .getField()
                .getNeighbors()
                .stream()
                .map(
                    (
                        field -> {
                            id[0]++;
                            return field.getType().name() + " (" + id[0] + ")";
                        }
                    )
                )
                .toArray(String[]::new);
            int fieldId = PopUpView.selectOption("Select a field to move to", "Virologist Move", options);
            if (fieldId == -1) return;
            virologist.move(fieldId);

            stepcounter++;
        } else {
            //System.out.println("You cannot do more steps in this round");
            PopUpView.showError("You cannot do more steps in this round", "Virologist Move");
        }
    }

    @Override
    public void murder(Virologist virologist) {
        if (virologist.getField().getVirologists().size() == 0) {
            PopUpView.showError("You can't kill anyone now!", "Virologist Kill");
        } else {
            String[] options = virologist.getField().getVirologists().stream().map(Virologist::getName).toArray(String[]::new);
            int virologistId = PopUpView.selectOption("Select a virologist to kill", "Virologist Kill", options);
            if (virologistId == -1) return;

            Virologist selectedVirologist = virologist.getField().getVirologists().get(virologistId);
            if (virologist.equals(selectedVirologist)) {
                PopUpView.showError("If you want to commit Harakiri, you have to buy the Harakiri DLC", "Virologist Kill");
            } else {
                virologist.murder(selectedVirologist);
            }
        }
    }

    @Override
    public void steal(Virologist virologist) {
        Collectable coll = null;

        String[] options = virologist.getField().getVirologists().stream().map(Virologist::getName).toArray(String[]::new);
        int virologistId = PopUpView.selectOption("Select a virologist to rob", "Virologist Steal", options);
        if (virologistId == -1) return;

        String stealType = PopUpView.selectOptionString(
            "What do you want to steal?",
            "Virologist Steal",
            new String[] { "Matter", "Equipment" }
        );

        if (stealType.equals("Matter")) {
            if (
                virologist.getField().getVirologists().get(virologistId).getNucleicAcid() == 0 &&
                virologist.getField().getVirologists().get(virologistId).getAminoAcid() == 0
            ) {
                PopUpView.showError("You cannot steal that kind of collectable", "Virologist Steal");
            } else {
                String acidType = PopUpView.selectOptionString(
                    "What to do want to steal?",
                    "Virologist Steal",
                    new String[] { "AminoAcid", "NucleicAcid" }
                );
                if (acidType.equals("AminoAcid")) {
                    if (virologist.getField().getVirologists().get(virologistId).getAminoAcid() == 0) {
                        PopUpView.showError("You cannot steal that kind of collectable", "Virologist Steal");
                    } else {
                        virologist.steal(
                            new AminoAcid(virologist.getField().getVirologists().get(virologistId).getAminoAcid()),
                            virologist.getField().getVirologists().get(virologistId)
                        );
                    }
                } else {
                    if (virologist.getField().getVirologists().get(virologistId).getNucleicAcid() == 0) {
                        PopUpView.showError("You cannot steal that kind of collectable", "Virologist Steal");
                    } else {
                        virologist.steal(
                            new NucleicAcid(virologist.getField().getVirologists().get(virologistId).getNucleicAcid()),
                            virologist.getField().getVirologists().get(virologistId)
                        );
                    }
                }
            }
        } else {
            if (virologist.getField().getVirologists().get(virologistId).getEquipments().size() == 0) {
                PopUpView.showError("You cannot steal that kind of collectable", "Virologist Steal");
            } else {
                options =
                    virologist
                        .getField()
                        .getVirologists()
                        .get(virologistId)
                        .getEquipments()
                        .stream()
                        .map(e -> e.getClass().getSimpleName())
                        .toArray(String[]::new);
                int selectedEquipment = PopUpView.selectOption("What do you want do steal?", "Virologist Steal", options);
                if (selectedEquipment != -1) {
                    virologist.steal(
                        virologist.getField().getVirologists().get(virologistId).getEquipments().get(selectedEquipment),
                        virologist.getField().getVirologists().get(virologistId)
                    );
                }
            }
        }
    }

    @Override
    public void discard(Virologist virologist) {
        String discarded = PopUpView.selectOptionString(
            "What do you want to discard?",
            "Virologist Discard",
            new String[] { "Matter", "Equipment" }
        );
        if (discarded.equals("Matter")) {
            String discardAcidType = PopUpView.selectOptionString(
                "What to do want to discard?",
                "Virologist Discard",
                new String[] { "AminoAcid", "NucleicAcid" }
            );
            if (discardAcidType.equals("AminoAcid")) {
                if (virologist.getAminoAcid() == 0) {
                    PopUpView.showError("You don't have any Amino Acid to discard", "Virologist Discard");
                } else {
                    int discard = PopUpView.numberInput(
                        "How much AminoAcid would you drop?",
                        "Virologist Discard",
                        1,
                        virologist.getAminoAcid()
                    );
                    virologist.discard(new AminoAcid(discard));
                }
            } else {
                if (virologist.getNucleicAcid() == 0) {
                    PopUpView.showError("You don't have any Nucleic Acid to discard", "Virologist Discard");
                } else {
                    int discard = PopUpView.numberInput(
                        "How much NucleicAcid would you drop?",
                        "Virologist Discard",
                        1,
                        virologist.getNucleicAcid()
                    );
                    virologist.discard(new NucleicAcid(discard));
                }
            }
        } else {
            if (virologist.getEquipments().size() == 0) {
                PopUpView.showError("You don't have any Equipment to discard", "Virologist Discard");
            } else {
                String[] options = virologist.getEquipments().stream().map(e -> e.getClass().getSimpleName()).toArray(String[]::new);
                int selectedEquipment = PopUpView.selectOption("What do you want do discard?", "Virologist Discard", options);
                if (selectedEquipment == -1) return;
                virologist.discard(virologist.getEquipments().get(selectedEquipment));
            }
        }
    }

    @Override
    public void createAgent(Virologist virologist) {
        if (virologist.getGencodes().size() != 0) {
            String[] options = virologist.getGencodes().stream().map(a -> a.getAgent().getClass().getSimpleName()).toArray(String[]::new);
            int selectedGencode = PopUpView.selectOption("Which gencode would you use?", "Virologist Create Agent", options);
            if (selectedGencode == -1) return;
            virologist.createAgent(virologist.getGencodes().get(selectedGencode));
        } else {
            PopUpView.showError("You don't have gencodes yet", "Virologist Create Agents");
        }
    }

    @Override
    public void useAgent(Virologist virologist) {
        if (virologist.getField().getVirologists().size() == 0 || virologist.getCreatedAgents().size() == 0) {
            PopUpView.showError("You can't attack anyone now!", "Virologist Use Agent");
        } else {
            //select the agent
            String[] options = virologist.getCreatedAgents().stream().map(a -> a.getClass().getSimpleName()).toArray(String[]::new);
            int selectedAgent = PopUpView.selectOption("Which agent would you use?", "Virologist Use Agent", options);

            options = virologist.getField().getVirologists().stream().map(Virologist::getName).toArray(String[]::new);
            int selectedTarget = PopUpView.selectOption("Who would you attack?", "Virologist Use Agent", options);
            if (selectedTarget == -1 || selectedAgent == -1) return;
            virologist.useAgent(
                virologist.getCreatedAgents().get(selectedAgent),
                virologist.getField().getVirologists().get(selectedTarget)
            );
        }
    }

    @Override
    public void collect(Virologist virologist) {
        Field field = virologist.getField();

        ArrayList<Collectable> collectables = field.getCollectable();
        if (collectables == null || collectables.size() == 0) {
            PopUpView.showError("You cannot collect anything from here", "Virologist Collect");
        } else {
            //List the collectables from the field
            String[] options = collectables
                .stream()
                .map(c -> {
                    if (c instanceof Gencode) {
                        return "[Agent] " + ((Gencode) c).getAgent().getClass().getSimpleName();
                    } else {
                        return c.getClass().getSimpleName();
                    }
                })
                .toArray(String[]::new);
            //Get the one what is wanted by the player
            int chosen = PopUpView.selectOption("What do you want to collect?", "Virologist Collect", options);
            if (chosen == -1) return;
            Collectable coll = collectables.get(chosen);

            //If the field is a shelter there can be restrictions
            if (field instanceof Shelter) {
                if (virologist.getEquipments().size() >= 3) {
                    PopUpView.showError("The inventory is full", "Virologist Collect");
                    return;
                }
                coll.collectBy(virologist);
                field.removeCollectable(coll);
            } else {
                //If it's not a shelter we can collect that thing
                coll.collectBy(virologist);
            }
        }
    }
    /**
     * Handles the round of the virologist by managing the possible actions issued by the player who controls the virologist
     * @param virologist the one whose round is being handled
     */
    /*@Override
    public void RoundRun(Virologist virologist) {
        String input;
        int stepcounter = 0;
        do {
            input =
                UserInputHandler.getUserInputString(
                    "What to do dear " + virologist.getName() + "?",
                    new String[] { "Move", "Collect", "Steal", "Discard", "Create Agent", "Use Agent", "Kill", "End Round", "Exit Game" }
                );
            switch (input) {
                //if the player wants to move
                case "Move":
                    if (stepcounter == 0) {
                        int n = 0;
                        for (Field f : virologist.getField().getNeighbors()) {
                            System.out.println(f.getClass().getSimpleName() + " command " + n);
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
                            System.out.println(g.getAgent().getClass().getSimpleName() + " command " + n);
                            n++;
                        }
                        int selectedGencode = UserInputHandler.getUserInputInt("Which gencode would you use?", 0, n - 1);
                        virologist.createAgent(virologist.getGencodes().get(selectedGencode));
                    } else {
                        System.out.println("You don't have gencodes yet");
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
                            System.out.println("Use " + ag.getClass().getSimpleName() + " agent: command " + a);
                            a++;
                        }
                        int selectedAgent = UserInputHandler.getUserInputInt("Which gencode do you want to use?", 0, a - 1);

                        //select the virologist to be attacket
                        int v = 0;
                        for (Virologist vir : virologist.getField().getVirologists()) {
                            System.out.println("Use agent on " + vir.getName() + " :command " + v);
                            v++;
                        }
                        int selectedvirologist = UserInputHandler.getUserInputInt("Who do you want to attack?", 0, v - 1);

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
                            System.out.println(vir.getName() + " command " + v);
                            v++;
                        }
                        int selectedvirologist = UserInputHandler.getUserInputInt("Who do you want to attack?", 0, v - 1);
                        if (virologist.equals(virologist.getField().getVirologists().get(selectedvirologist))) System.out.println(
                            "If you want to commit Harakiri, you have to buy the Harakiri DLC"
                        ); else virologist.murder(virologist.getField().getVirologists().get(selectedvirologist));
                    }
                    break;
                case "Steal":
                    Collectable coll = null;
                    int v = 0;
                    for (Virologist vir : virologist.getField().getVirologists()) {
                        if (!vir.getName().equals(virologist.getName())) System.out.println(
                            "Steal from " + vir.getName() + " :command " + v
                        );
                        v++;
                    }
                    int selectedvirologist = UserInputHandler.getUserInputInt("Who do you want to rob?", 0, v - 1);
                    String in = UserInputHandler.getUserInputString("What do you want to steal?", new String[] { "Matter", "Equipment" });
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
                                System.out.println("Steal " + eq.getClass().getSimpleName() + " :command " + e);
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
                case "Discard":
                    String discarded = UserInputHandler.getUserInputString(
                        "What do you want to discard?",
                        new String[] { "Matter", "Equipment" }
                    );
                    if (discarded.equals("Matter")) {
                        in =
                            UserInputHandler.getUserInputString("What to do want to discard?", new String[] { "AminoAcid", "NucleicAcid" });
                        if (in.equals("AminoAcid")) {
                            if (virologist.getAminoAcid() == 0) System.out.println("You cannot discard that kind of collectable"); else {
                                int discard = UserInputHandler.getUserInputInt(
                                    "How much AminoAcid would you drop?",
                                    1,
                                    virologist.getAminoAcid()
                                );
                                virologist.discard(new AminoAcid(discard));
                            }
                        } else {
                            if (virologist.getNucleicAcid() == 0) System.out.println("You cannot discard that kind of collectable"); else {
                                int discard = UserInputHandler.getUserInputInt(
                                    "How much NucleicAcid would you drop?",
                                    1,
                                    virologist.getNucleicAcid()
                                );
                                virologist.discard(new NucleicAcid(discard));
                            }
                        }
                    } else {
                        if (virologist.getEquipments().size() == 0) System.out.println("You cannot steal that kind of collectable"); else {
                            int e = 0;
                            for (Equipment eq : virologist.getEquipments()) {
                                System.out.println("Discard " + eq.getClass().getSimpleName() + " :command " + e);
                                e++;
                            }
                            int selectedequipment = UserInputHandler.getUserInputInt("What do you want do discard?", 0, e - 1);
                            virologist.discard(virologist.getEquipments().get(selectedequipment));
                        }
                    }
                    break;
                case "Exit Game":
                    boolean shouldSaveGame = UserInputHandler.getUserInputBoolean("Do you want to save the game state?");
                    if (shouldSaveGame) {
                        String mapName = UserInputHandler.getUserInputString("Enter a name for the save: ");
                        GameController.getInstance().saveGame(mapName);
                    }
                    System.out.println("You have exited the game");
                    System.exit(0);
                    return;
                default:
                    break;
            }
        } while (!input.equals("End Round"));
        virologist.emptyCreatedAgents();
    }*/
}
