package com.csb.virologist;

import com.csb.agents.*;
import com.csb.collectables.Collectable;
import com.csb.collectables.equipments.Equipment;
import com.csb.collectables.gencodes.Gencode;
import com.csb.collectables.matters.AminoAcid;
import com.csb.collectables.matters.NucleicAcid;
import com.csb.fields.Field;
import com.csb.fields.Shelter;
import com.csb.gameControl.GameController;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.strategies.*;
import com.csb.strategies.DefenseStrategyInterface;
import com.csb.strategies.MoveStrategyInterface;
import com.csb.strategies.RoundRunStrategyInterface;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This is the Virologist class. The players in the game contol Virologists, so most of the actions can be connected to this class
 * In it's variables we can found the strategies using by the Virologist at a given moment, the default strategies,
 * it's Aminoacid and Nucleicacid stock, the equipments used by the virologists, and the Gencodes learned by him, and the Agents made by him.
 */

public class Virologist implements Serializable {

    private String name;
    private final ArrayList<DefenseStrategyInterface> defenseStrategy;
    private final ArrayList<RoundRunStrategyInterface> roundRunStrategy;
    private final ArrayList<MoveStrategyInterface> moveStrategy;
    private final ArrayList<MurderStrategyInterface> murderStrategy;
    private int inventorySize;
    private final AminoAcid aminoAcidStock;
    private final NucleicAcid nucleicAcidStock;
    private ArrayList<Gencode> gencodes;

    //The agents, having effect on the Virologist
    private ArrayList<Agent> agentlist;

    //The agents created by the virologist
    private ArrayList<Agent> createdagents;
    private final ArrayList<Equipment> equipments;
    private Field field;

    /**
     * In the constructor of the Virologist we can implement a variables, set a strategies to default, and set the starting size of the inventory
     */
    public Virologist(String name) {
        inventorySize = 10;
        nucleicAcidStock = new NucleicAcid(0);
        aminoAcidStock = new AminoAcid(0);
        gencodes = new ArrayList<Gencode>();
        defenseStrategy = new ArrayList<DefenseStrategyInterface>();
        roundRunStrategy = new ArrayList<RoundRunStrategyInterface>();
        moveStrategy = new ArrayList<MoveStrategyInterface>();
        murderStrategy = new ArrayList<MurderStrategyInterface>();
        agentlist = new ArrayList<Agent>();
        defenseStrategy.add(new DefaultDefense());
        roundRunStrategy.add(new DefaultRoundRun());
        moveStrategy.add(new DefaultMove());
        murderStrategy.add(new DefaultMurder());
        equipments = new ArrayList<Equipment>(3);
        createdagents = new ArrayList<Agent>();
        this.name = name;
    }

    /**
     * This functions sets the defense strategy of the virologist
     * @param defenseStrategy the defense strategy to be set on the virologist
     */
    public void setDefenseStrategy(DefenseStrategyInterface defenseStrategy) {
        this.defenseStrategy.add(defenseStrategy);
    }

    /**
     * When the virologist gets attacked, this function handles it
     * @param agent the agent that the virologist is attacked with
     * @param attackerVirologist the virologist, who initiated an attack
     */
    public void attack(Agent agent, Virologist attackerVirologist) {
        defenseStrategy.get(defenseStrategy.size() - 1).defense(agent, this, attackerVirologist);
    }

    /**
     * Handles one turn of the virologist
     */
    public void startOfTurn() {
        getRoundRunStrategy().roundRun();
        GameController.getInstance().reportGencodes(this, this.getGencodes());
        this.refreshAgents();
    }

    /**
     * Get the current used RoundRunStrategy from the virologist
     * @return the current used RoundRunStrategy
     */
    public RoundRunStrategyInterface getRoundRunStrategy() {
        return roundRunStrategy.get(roundRunStrategy.size() - 1);
    }

    /**
     * The virologist get the collectable item from the Filed where he stands, and one of the findings (int this example the first one)
     * @param field - the field, where the Viologist make research work after collectable items
     */
    public void collect(Field field) {
        ArrayList<Collectable> collectables = field.getCollectable();
        if (collectables == null || collectables.size() == 0) System.out.println("You cannot collect anything from here"); else {
            //List the collectables from the field
            for (int i = 0; i < collectables.size(); i++) {
                if (collectables.get(i) instanceof Gencode) System.out.println(
                    "Collect: the gencode for " +
                    ((Gencode) collectables.get(i)).getAgent().getClass().getSimpleName() +
                    "agent. Command: " +
                    i
                ); else {
                    System.out.println("Collect: " + collectables.get(i).getClass().getSimpleName() + " Command: " + i);
                }
            }

            //Get the one what is wanted by the player
            int chosen = UserInputHandler.getUserInputInt("What do you want to collect?", 0, collectables.size() - 1);
            Collectable coll = collectables.get(chosen);

            //if the field is a shelter there can be restricions
            if (field instanceof Shelter) {
                if (equipments.size() >= 3) {
                    System.out.println("The inventory is full");
                    return;
                }
                coll.collectBy(this);
                field.removeCollectable(coll);
            }
            //if its not a shelter we can collect that thing
            else {
                coll.collectBy(this);
            }
        }
    }

    /**
     * The virologist uses a chosen agent on a chosen virologist, this can either be another one or themselves
     * If the user virologist has gloves on, he takes them down, to avoid a ping-pong like effect when the 2 virologists have gloves on
     * @param agent the agent to be used by the virologist
     * @param targetVirologist the virologist that the agent would be applied on
     */
    public void useAgent(Agent agent, Virologist targetVirologist) {
        System.out.println(this.getName() + " has used agent: " + agent.getClass().getSimpleName() + " on " + targetVirologist.getName());
        targetVirologist.attack(agent, this);
        createdagents.remove(agent);
    }

    /**
     * This functions sets the roundrun strategy of the virologist
     * @param roundRunStrategy the roundrun strategy to be set on the virologist, this affects his round and defense against to be robbed
     */
    public void setRoundRunStrategy(RoundRunStrategyInterface roundRunStrategy) {
        this.roundRunStrategy.add(roundRunStrategy);
    }

    /**
     * This functions sets the move strategy of the virologist
     * @param moveStrategy the move strategy to be set on the virologist, this affects the way he moves
     */
    public void setMoveStrategy(MoveStrategyInterface moveStrategy) {
        if (!this.moveStrategy.get(this.moveStrategy.size() - 1).getClass().getSimpleName().equals("BearDance")) this.moveStrategy.add(
                moveStrategy
            ); else this.moveStrategy.add(0, moveStrategy);
    }

    /**
     * This functions sets the move strategy of the virologist
     * @param murderStrategy the move strategy to be set on the virologist, this affects the way he moves
     */
    public void setMurderStrategy(MurderStrategyInterface murderStrategy) {
        this.murderStrategy.add(murderStrategy);
    }

    /**
     * Function what gives the size of the Virologist's matter collect limit
     * @return - size of the Virologist's inventory - the sum of the collectable AminoAcid and NucleicAcid
     */
    public int getInventorySize() {
        return inventorySize;
    }

    /**
     * Set a new inventory size for the Virologist
     * @param inventorySize - The inventory size to be set
     */
    public void setInventorySize(int inventorySize) {
        this.inventorySize = inventorySize;
    }

    /**
     * @return AminoAcid owned by the Virologist
     */
    public int getAminoAcid() {
        return aminoAcidStock.getAmount();
    }

    /**
     * @param aminoAcid - the AminoAcid amount to be set
     */
    public void setAminoAcid(int aminoAcid) {
        aminoAcidStock.setAmount(aminoAcid);
    }

    /**
     * @return NucleicAcid owned by the Virologist
     */
    public int getNucleicAcid() {
        return nucleicAcidStock.getAmount();
    }

    /**
     * @param nucleicAcid - the NucleicAcid amount to be set
     */
    public void setNucleicAcid(int nucleicAcid) {
        nucleicAcidStock.setAmount(nucleicAcid);
    }

    /**
     * @return - the Gencodes learned by the Virologist
     */
    public ArrayList<Gencode> getGencodes() {
        return gencodes;
    }

    public void addGencode(Gencode gencode) {
        gencodes.add(gencode);
    }

    /**
     * Set the Gencodes learned by the virologist
     * @param gencodes the Gencode list to be set
     */
    public void setGencodes(ArrayList<Gencode> gencodes) {
        this.gencodes = gencodes;
    }

    /**
     * By this function a Virologist try to steal an Equipment or some Matter from an other Virologist
     * The attempt can be successful, if the target Virologist is under the effect of the Paralyze Agent
     * @param coll - The item wanted ba the robber Virologist from the target Virologist
     * @param targetVirologist - The Virologist, who has the item wanted by the robber
     */
    public void steal(Collectable coll, Virologist targetVirologist) {
        //the target virologist handle the theft
        Collectable stolen = targetVirologist.handleSteal(coll);

        if (stolen == null) {
            System.out.println("The robbery was not succesful");
            return;
        }

        //the robber virologist collects the loot, if the attempt was succesful
        System.out.println(this.getName() + " has stolen " + stolen.getClass().getSimpleName() + " from " + targetVirologist.getName());
        stolen.collectBy(this);
    }

    /**
     * The virologist drop ang Equipment or an amount of Matter from its collection
     * @param coll - the item to be discarded
     */
    public void discard(Collectable coll) {
        coll.discard(this);
    }

    /**
     * Create an agent if the virologist has enough materials (amino acid and nucleic acid)
     * @param genCode - The Gencode of the agent to be created
     */
    public void createAgent(Gencode genCode) {
        if (
            this.aminoAcidStock.getAmount() >= genCode.getRequiredAminoAcid().getAmount() &&
            this.nucleicAcidStock.getAmount() >= genCode.getRequiredNucleicAcid().getAmount()
        ) {
            this.aminoAcidStock.setAmount(this.getAminoAcid() - genCode.getRequiredAminoAcid().getAmount());
            this.nucleicAcidStock.setAmount(this.getNucleicAcid() - genCode.getRequiredNucleicAcid().getAmount());
            this.createdagents.add(genCode.getAgent());
            System.out.println(this.name + " has created " + genCode.getAgent().getClass().getSimpleName());
        } else {
            System.out.println("You don't have enough materials.");
        }
    }

    /**
     * Adds an agent to the current agents of the virologist
     * @param agent the agent to be put in the bag
     */
    public void storeAgent(Agent agent) {
        this.agentlist.add(agent);
    }

    /**
     * Decrease the remaining time of the applied agents on the virologist
     */
    public void refreshAgents() {
        for (int i = 0; i < agentlist.size(); i++) {
            agentlist.get(i).decreaseTTL(this);
            if (agentlist.get(i).getTTL() == 0) agentlist.remove(agentlist.get(i));
            //if(agentlist.get(i) == null) continue;
        }
    }

    public void setAgentlist(ArrayList<Agent> agentlist) {
        this.agentlist = agentlist;
    }

    public ArrayList<Agent> getAgentlist() {
        return this.agentlist;
    }

    /**
     * @return - the equipments owned by the Virologist
     */
    public ArrayList<Equipment> getEquipments() {
        return equipments;
    }

    /**
     * Add an Equipment to the Virologist
     * @param e the equipment to be added
     */
    public void addEquipment(Equipment e) {
        equipments.add(e);
    }

    /**
     * Remove an Equipment from the Virologist
     * @param e the equipment to be removed
     */
    public void removeEquipment(Equipment e) {
        equipments.remove(e);
    }

    /**
     * The Virologist handles the robbing attemps based on his (roundrun) strategy
     * @param coll - the item wanted by the robber Virologist
     * @return - the item, which the robber Virologist has stolen
     */
    public Collectable handleSteal(Collectable coll) {
        return this.roundRunStrategy.get(roundRunStrategy.size() - 1).handleSteal(coll, this);
    }

    /**
     * Move the Virologist to a new position
     * @param nextTileIndex - the index of the new position
     */
    public void move(int nextTileIndex) {
        this.moveStrategy.get(moveStrategy.size() - 1).move(this, nextTileIndex);
    }

    /**
     * Removes the defenseStrategy given in the parameters
     * @param defenseStrategy - the defense strategy to be removed
     */
    public void removeDefenseStrategy(DefenseStrategyInterface defenseStrategy) {
        this.defenseStrategy.remove(defenseStrategy);
    }

    /**
     * Removes the defenseStrategy given in the parameters
     * @param murderStrategy - the defense strategy to be removed
     */
    public void removeMurderStrategy(MurderStrategyInterface murderStrategy) {
        this.murderStrategy.remove(murderStrategy);
    }

    /**
     * Removes the moveStrategy given in the parameters
     * @param moveStrategyInterface - the move strategy to be removed
     */
    public void removeMoveStrategy(MoveStrategyInterface moveStrategyInterface) {
        this.moveStrategy.remove(moveStrategyInterface);
    }

    /**
     * Removes the roundRunStrategy given in the parameters
     * @param roundRunStrategyInterface - the roundrun strategy to be removed
     */
    public void removeRoundRunStrategy(RoundRunStrategyInterface roundRunStrategyInterface) {
        this.roundRunStrategy.remove(roundRunStrategyInterface);
    }

    /**
     * Sets the filed the Virologist is on
     * @param field - the field the Virologist is on
     */
    public void setField(Field field) {
        this.field = field;
    }

    /**
     * Gets the Virologist's current field
     * @return - the Virologist's current field
     */
    public Field getField() {
        return field;
    }

    /**
     * Eliminate the virologist from the game
     */
    public void die() {
        GameController.getInstance().reportDeath(this);
    }

    /**
     *clear the created agents
     */
    public void emptyCreatedAgents() {
        createdagents.clear();
    }

    /**
     * @return the agents created by the virologist before
     */
    public ArrayList<Agent> getCreatedAgents() {
        return createdagents;
    }

    /**
     * @return the name of the virologist
     */
    public String getName() {
        return name;
    }

    /**
     *A virologist try to murder another
     * @param victim the murder wants to kill him
     */
    public void murder(Virologist victim) {
        this.murderStrategy.get(murderStrategy.size() - 1).murder(this, victim);
    }
}
