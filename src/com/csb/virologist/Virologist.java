package com.csb.virologist;

import com.csb.agents.*;
import com.csb.collectables.Collectable;
import com.csb.collectables.equipments.Equipment;
import com.csb.collectables.equipments.Gloves;
import com.csb.collectables.gencodes.Gencode;
import com.csb.collectables.matters.AminoAcid;
import com.csb.collectables.matters.NucleicAcid;
import com.csb.collectables.matters.AminoAcid;
import com.csb.collectables.matters.NucleicAcid;
import com.csb.collectables.gencodes.Gencode;
import com.csb.fields.Field;
import com.csb.skeletonTester.Tester;
import com.csb.strategies.DefenseStrategyInterface;
import com.csb.strategies.MoveStrategyInterface;
import com.csb.strategies.RoundRunStrategyInterface;

import com.csb.strategies.*;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * This is the Virologist class. The players in the game contol Virologists, so most of the actions can be connected to this class
 *In it's variables we can found the strategies using by the Virologist at a given moment, the default strategies,
 * it's Aminoacid and Nucleicacid stock, the equipments used by the virologists, and the Gencodes learned by him, and the Agents made by him.
 */

public class Virologist {

    private DefenseStrategyInterface defenseStrategy;
    private RoundRunStrategyInterface roundRunStrategy;
    private MoveStrategyInterface moveStrategy;
    private DefenseStrategyInterface defaultDefenseStrategy;
    private MoveStrategyInterface defaultMoveStrategy;
    private RoundRunStrategyInterface defaultRoundRunStrategy;
    private int inventorySize;
    private AminoAcid aminoAcidStock;
    private NucleicAcid nucleicAcidStock;
    private ArrayList<Gencode> gencodes;
    private ArrayList<Agent> agentlist;
    private ArrayList<Equipment> equipments;

    /**
     * In the constructor of the Virologist we can implement a variables, set a strategies to default, and set the starting size of the inventory
     */
    public Virologist() {
        inventorySize = 30;
        nucleicAcidStock=new NucleicAcid(0);
        aminoAcidStock=new AminoAcid(0);
        gencodes=new ArrayList<Gencode>();
        defaultDefenseStrategy=new DefaultDefense();
        defaultRoundRunStrategy=new DefaultRoundRun();
        defaultMoveStrategy=new DefaultMove();
        defenseStrategy=defaultDefenseStrategy;
        roundRunStrategy=defaultRoundRunStrategy;
        moveStrategy=defaultMoveStrategy;
        equipments=new ArrayList<Equipment>(3);

    }

    /**
     * This functions sets the defense strategy of the virologist
     * @param defenseStrategy the defense strategy to be set on the virologist
     */
    public void setDefenseStrategy(DefenseStrategyInterface defenseStrategy) {
        Tester.getInstance().functionStart();
        if(defenseStrategy != null) {
            this.defenseStrategy = defenseStrategy;
        }
        Tester.getInstance().functionEnd();
    }


    public void attack(Agent agent, Virologist attackerVirologist) {
        Tester.getInstance().functionStart();
        defenseStrategy.defense(agent,this, attackerVirologist);
        Tester.getInstance().functionEnd();
    }


    /**
     *The virologist get the collectable item from the Filed where he stands, and one of the findings (int this example the first one)
     * @param field - the field, where the Viologist make research work after collectable items
     */
    public void collect(Field field) {
        Tester.getInstance().functionStart();
        ArrayList<Collectable> collectables = field.getCollectable();
        if (collectables != null) {
            collectables.get(0).collectBy(this);
        }
        Tester.getInstance().functionEnd();
    }


    public void useAgent(Agent agent, Virologist targetVirologist) {
        Tester.getInstance().functionStart();
        Gloves g=new Gloves();
        if (defenseStrategy.equals(g)){
            g.removeEffect(this);
            targetVirologist.attack(agent,  this);
            g.applyEffect(this);
        }

        else targetVirologist.attack(agent,  this);

        Tester.getInstance().functionEnd();
    }


    /**
     * This functions sets the roundrun strategy of the virologist
     * @param roundRunStrategy the roundrun strategy to be set on the virologist, this affects his round and defense against to be robbed
     */
    public void setRoundRunStrategy(RoundRunStrategyInterface roundRunStrategy){
        Tester.getInstance().functionStart();
        if(roundRunStrategy!=null) {
            this.roundRunStrategy = roundRunStrategy;
        }
        Tester.getInstance().functionEnd();
    }

    /**
     * This functions sets the move strategy of the virologist
     * @param moveStrategy the move strategy to be set on the virologist, this affects the way he moves
     */
    public void setmoveStrategy(MoveStrategyInterface moveStrategy){
        Tester.getInstance().functionStart();
        if(moveStrategy!=null) {
            this.moveStrategy = moveStrategy;
        }
        Tester.getInstance().functionEnd();
    }

    /**
     * Function what gives the size of the Virologist's matter collect limit
     * @return - size of the Virologist's inventory - the sum of the collectable AminoAcid and NucleicAcid
     */
    public int getInventorySize() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return inventorySize;
    }

    /**
     * Set a new inventory size for the Virologist
     * @param inventorySize - The inventory size to be set
     */
    public void setInventorySize(int inventorySize) {
        Tester.getInstance().functionStart();
        this.inventorySize = inventorySize;
        Tester.getInstance().functionEnd();
    }

    /**
     * @return AminoAcid owned by the Virologist
     */
    public int getAminoAcid() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return aminoAcidStock.getAmount();

    }

    /**
     * @param aminoAcid - the AminoAcid amount to be set
     */
    public void setAminoAcid(int aminoAcid) {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        aminoAcidStock.setAmount(aminoAcid);
    }

    /**
     * @return NucleicAcid owned by the Virologist
     */
    public int getNucleicAcid() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return nucleicAcidStock.getAmount();
    }

    /**
     * @param nucleicAcid - the NucleicAcid amount to be set
     */
    public void setNucleicAcid(int nucleicAcid) {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        nucleicAcidStock.setAmount(nucleicAcid);
    }

    /**
     * @return - the Gencodes learned by the Virologist
     */
    public ArrayList<Gencode> getGencodes() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return gencodes;
    }

    /**
     * Set the Gencodes learned by the virologist
     * @param gencodes the Gencode list to be set
     */
    public void setGencodes(ArrayList<Gencode> gencodes) {
        Tester.getInstance().functionStart();
        this.gencodes = gencodes;
        Tester.getInstance().functionEnd();
    }

    /**
     * By this function a Virologist try to steal an Equipment or some Matter from an other Virologist
     * The attempt can be successful, if the target Virologist is under the effect of the Paralyze Agent
     * @param coll - The item wanted ba the robber Virologist from the target Virologist
     * @param targetVirologist - The Virologist, who has the item wanted by the robber
     */
    public void steal(Collectable coll, Virologist targetVirologist ){
        Tester.getInstance().functionStart();

        //the target virologist handle the theft
        Collectable stolen=targetVirologist.handleSteal(coll);

        if(stolen==null)
        {
            Tester.getInstance().functionEnd();
            return;
        }

        //the robber virologist collects the loot, if the attempt was succesful
        stolen.collectBy(this);
        Tester.getInstance().functionEnd();
    }

    /**
     * The virologist drop ang Equipment or an amount of Matter from its collection
     * @param coll - the item to be discarded
     */
    public void discard(Collectable coll){
        coll.discard(this);
    }


    public void createAgent(Gencode genCode){
        Tester.getInstance().functionStart();
        genCode.getRequiredNucleicAcid();
        genCode.getRequiredAminoAcid();
        this.storeAgent(genCode.getAgent());
        Tester.getInstance().functionEnd();
    }
    public void storeAgent(Agent agent){
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
    }

    public void refreshAgents(){
        Tester.getInstance().functionStart();
        for(Agent agent: agentlist){
            agent.decreaseTTL(this);
        }
        Tester.getInstance().functionEnd();
    }
    public void setAgentlist(ArrayList<Agent> agentlist){
        this.agentlist=agentlist;
    }

    /**
     * @return - the default defending strategy
     */
    public DefenseStrategyInterface getDefaultDefenseStrategy(){
        return defaultDefenseStrategy;
    }

    /**
     * @return - the default moving strategy
     */
    public MoveStrategyInterface getDefaultMoveStrategy(){
        return defaultMoveStrategy;
    }

    /**
     * @return - the default RoundRun strategy
     */
    public RoundRunStrategyInterface getDefaultRoundRunStrategy(){
        return defaultRoundRunStrategy;
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
    public void addEquipment(Equipment e){
        equipments.add(e);
    };

    /**
     * Remove an Equipment from the Virologist
     * @param e the equipment to be removed
     */
    public void removeEquipment(Equipment e){
        equipments.remove(e);
    };

    /**
     * The Virologist handles the robbing attemps based on his (roundrun) strategy
     * @param coll - the item wanted by the robber Virologist
     * @return
     */
    public Collectable handleSteal(Collectable coll){
       return this.roundRunStrategy.handleSteal(coll, this);
    }

    public void setAgent(){
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
    }
}
