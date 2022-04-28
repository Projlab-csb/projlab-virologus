package com.csb.controller;

import com.csb.view.VirologistView;
import com.csb.virologist.Virologist;

public class VirologistController {

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

    //For each user action we must create a function like this one in the specific controller
    //And from the defaultRoundRun strategy we can call these functions
    public void move(int nextTileId) {
        virologist.move(nextTileId);
        updateView();
    }

    /**
     * Update all view components about the virologist
     */
    public void updateView() {
        if (virologist != null) {
            view.printName(virologist.getName());
            view.renderMap(virologist.getField());
        }
    }
}
