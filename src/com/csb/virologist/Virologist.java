package com.csb.virologist;

import com.csb.agents.*;
import com.csb.collectables.Collectable;
import com.csb.collectables.equipments.Equipment;
import com.csb.collectables.equipments.Gloves;
import com.csb.collectables.gencodes.Gencode;
import com.csb.collectables.matters.AminoAcid;
import com.csb.collectables.matters.NucleicAcid;
import com.csb.fields.Field;
import com.csb.gameControl.GameController;
import com.csb.skeletonTester.Tester;
import com.csb.strategies.*;
import com.csb.strategies.DefenseStrategyInterface;
import com.csb.strategies.MoveStrategyInterface;
import com.csb.strategies.RoundRunStrategyInterface;
import java.util.ArrayList;

/**
 * This is the Virologist class. The players in the game contol Virologists, so most of the actions can be connected to this class
 * In it's variables we can found the strategies using by the Virologist at a given moment, the default strategies,
 * it's Aminoacid and Nucleicacid stock, the equipments used by the virologists, and the Gencodes learned by him, and the Agents made by him.
 */

public class Virologist {

    private final ArrayList<DefenseStrategyInterface> defenseStrategy;
    private final ArrayList<RoundRunStrategyInterface> roundRunStrategy;
    private final ArrayList<MoveStrategyInterface> moveStrategy;
    //private final DefenseStrategyInterface defaultDefenseStrategy;
    //private final MoveStrategyInterface defaultMoveStrategy;
    //private final RoundRunStrategyInterface defaultRoundRunStrategy;
    private int inventorySize;
    private final AminoAcid aminoAcidStock;
    private final NucleicAcid nucleicAcidStock;
    private ArrayList<Gencode> gencodes;
    private ArrayList<Agent> agentlist;
    private final ArrayList<Equipment> equipments;
    private Field field;

    /**
     * In the constructor of the Virologist we can implement a variables, set a strategies to default, and set the starting size of the inventory
     */
    public Virologist() {
        inventorySize = 30;
        nucleicAcidStock = new NucleicAcid(0);
        aminoAcidStock = new AminoAcid(0);
        gencodes = new ArrayList<Gencode>();
        // defaultDefenseStrategy = new DefaultDefense();
        // defaultRoundRunStrategy = new DefaultRoundRun();
        // defaultMoveStrategy = new DefaultMove();
        defenseStrategy = new ArrayList<DefenseStrategyInterface>();
        roundRunStrategy = new ArrayList<RoundRunStrategyInterface>();
        moveStrategy = new ArrayList<MoveStrategyInterface>();
        agentlist = new ArrayList<Agent>();
        defenseStrategy.add(new DefaultDefense());
        roundRunStrategy.add(new DefaultRoundRun());
        moveStrategy.add(new DefaultMove());
        equipments = new ArrayList<Equipment>(3);
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
        this.roundRunStrategy.get(roundRunStrategy.size() - 1).RoundRun(this);
        GameController.getInstance().reportGencodes(this, this.getGencodes());
        this.refreshAgents();
    }

    /**
     * The virologist get the collectable item from the Filed where he stands, and one of the findings (int this example the first one)
     * @param field - the field, where the Viologist make research work after collectable items
     */
    public void collect(Field field) {
        ArrayList<Collectable> collectables = field.getCollectable();
        if (collectables != null) {
            collectables.get(0).collectBy(this);
        }
    }

    /**
     * The virologist uses a chosen agent on a chosen virologist, this can either be another one or themselves
     * If the user virologist has gloves on, he takes them down, to avoid a ping-pong like effect when the 2 virologists have gloves on
     * @param agent the agent to be used by the virologist
     * @param targetVirologist the virologist that the agent would be applied on
     */
    public void useAgent(Agent agent, Virologist targetVirologist) {
        Gloves g = new Gloves();
        if (!this.field.equals(targetVirologist.field)) return;
        //If both virologists have gloves on, we don't use the agent
        if (
            defenseStrategy.stream().anyMatch(x -> x.getClass().equals(Gloves.class)) &&
            targetVirologist.defenseStrategy.stream().anyMatch(x -> x.getClass().equals(Gloves.class))
        ) {
            return;
        }

        targetVirologist.attack(agent, this);

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
        this.moveStrategy.add(moveStrategy);
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
            return;
        }

        //the robber virologist collects the loot, if the attempt was succesful
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
        if(this.aminoAcidStock.getAmount() >= genCode.getRequiredAminoAcid().getAmount() && this.nucleicAcidStock.getAmount() >= genCode.getRequiredNucleicAcid().getAmount()){
            this.storeAgent(genCode.getAgent());
        }else{
            System.out.println("You don't have enough material.");
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
        for (Agent agent : agentlist) {
            agent.decreaseTTL(this);
        }
    }

    public void setAgentlist(ArrayList<Agent> agentlist) {
        this.agentlist = agentlist;
    }

    /**
     * @return - the default defending strategy
     */
    /*public DefenseStrategyInterface getDefaultDefenseStrategy() {
        return defaultDefenseStrategy;
    }*/

    /**
     * @return - the default moving strategy
     */
    /*public MoveStrategyInterface getDefaultMoveStrategy() {
        return defaultMoveStrategy;
    }*/

    /**
     * @return - the default RoundRun strategy
     */
    /* public RoundRunStrategyInterface getDefaultRoundRunStrategy() {
        return defaultRoundRunStrategy;
    }*/

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
        field.acceptVirologist(this);
    }

    /**
     * Gets the Virologist's current field
     * @return - the Virologist's current field
     */
    public Field getField() {
        return field;
    }
}
