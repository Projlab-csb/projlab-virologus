package com.csb.controller;

import com.csb.gameControl.GameController;
import com.csb.view.VirologistView;
import com.csb.virologist.Virologist;
import java.io.Serializable;

public class VirologistController implements Serializable {

    private Virologist virologist;
    private final VirologistView view;

    public VirologistController(Virologist virologist, VirologistView view) {
        this.virologist = virologist;
        this.view = view;
    }

    /**setter and getter for the virologist**/
    public void setVirologist(Virologist virologist) {
        this.virologist = virologist;
    }

    public Virologist getVirologist() {
        return virologist;
    }

    //End setter/getter -----------------------------------------------------------

    public void roundRun() {
        virologist.startOfTurn();
        updateView();
    }

    /**ends the current round and starts the next round**/
    public void roundEnd() {
        GameController.getInstance().nextPlayersTurn();
        updateView();
    }

    /**moves the virologist**/
    public void move() {
        virologist.getRoundRunStrategy().move(virologist);
        updateView();
    }

    /**calls the default murder function**/
    public void murder() {
        virologist.getRoundRunStrategy().murder(virologist);
        updateView();
    }

    /**calls the default steal function**/
    public void steal() {
        virologist.getRoundRunStrategy().steal(virologist);
        updateView();
    }

    /**calls the default discard function**/
    public void discard() {
        virologist.getRoundRunStrategy().discard(virologist);
        updateView();
    }

    /**calls the default agent creator function on the virologist**/
    public void createAgent() {
        virologist.getRoundRunStrategy().createAgent(virologist);
        updateView();
    }

    /**makes the virologist use its agent**/
    public void useAgent() {
        virologist.getRoundRunStrategy().useAgent(virologist);
        updateView();
    }

    /**calls the default collect function on the virologist**/
    public void collect() {
        virologist.getRoundRunStrategy().collect(virologist);
        updateView();
    }

    /**
     * Update all view components about the virologist
     */
    public void updateView() {
        if (virologist != null) {
            view.renderMap();
            view.printVirologist(virologist);
        }
    }
}
