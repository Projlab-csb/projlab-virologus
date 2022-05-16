package com.csb.controller;

import com.csb.gameControl.GameController;
import com.csb.view.VirologistView;
import com.csb.virologist.Virologist;

import java.io.Serial;
import java.io.Serializable;

public class VirologistController implements Serializable {

    private Virologist virologist;
    private final VirologistView view;

    public VirologistController(Virologist virologist, VirologistView view) {
        this.virologist = virologist;
        this.view = view;
    }

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

    public void roundEnd() {
        GameController.getInstance().nextPlayersTurn();
        updateView();
    }

    public void move() {
        virologist.getRoundRunStrategy().move(virologist);
        updateView();
    }

    public void murder() {
        virologist.getRoundRunStrategy().murder(virologist);
        updateView();
    }

    public void steal() {
        virologist.getRoundRunStrategy().steal(virologist);
        updateView();
    }

    public void discard() {
        virologist.getRoundRunStrategy().discard(virologist);
        updateView();
    }

    public void createAgent() {
        virologist.getRoundRunStrategy().createAgent(virologist);
        updateView();
    }

    public void useAgent() {
        virologist.getRoundRunStrategy().useAgent(virologist);
        updateView();
    }

    public void collect() {
        virologist.getRoundRunStrategy().collect(virologist);
        updateView();
    }

    /**
     * Update all view components about the virologist
     */
    public void updateView() {
        if (virologist != null) {
            view.printVirologist(virologist);
            view.renderMap();
        }
    }
}
