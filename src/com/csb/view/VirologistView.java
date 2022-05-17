package com.csb.view;

import com.csb.fields.Field;
import com.csb.gameControl.GameController;
import com.csb.virologist.Virologist;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.*;

/**
 * TODO: Maybe this should be a renderable panel, and the view should hold all the virologist's dependent UI element
 */
public class VirologistView implements Serializable {

    private ArrayList<JLabel> labels = new ArrayList<JLabel>();
    private MapPanel mapPanel;

    public VirologistView(ArrayList<JLabel> label, MapPanel mapPanel) {
        labels = label;
        this.mapPanel = mapPanel;
    }

    /**
     * Render label with the given name
     * @param v the virologist to be rendered
     */
    public void printVirologist(Virologist v) {
        String out = "";
        this.labels.get(0).setText("Name: " + v.getName() + "\n");
        this.labels.get(1).setText("Amino Acid Level: " + v.getAminoAcid() + "\n");
        this.labels.get(2).setText("Nucleic Acid Level: " + v.getNucleicAcid() + "\n");

        for (int i = 0; i < v.getEquipments().size(); i++) {
            out += "<br/>" + v.getEquipments().get(i).getClass().getSimpleName();
        }

        this.labels.get(3).setText("<html>Inventory:" + out + "<br/><br/></html>");

        out = "";
        for (int i = 0; i < v.getGencodes().size(); i++) {
            out += "<br/>" + v.getGencodes().get(i).getAgent().getClass().getSimpleName();
        }
        this.labels.get(4).setText("<html>Genetic codes:" + out + "<br/><br/></html>");

        out = "";
        for (int i = 0; i < v.getCreatedAgents().size(); i++) {
            out += "<br/>" + v.getCreatedAgents().get(i).getClass().getSimpleName();
        }
        this.labels.get(5).setText("<html>Created agents:" + out + "<br/><br/></html>");

        out = "";
        for (int i = 0; i < v.getAgentlist().size(); i++) {
            out += "<br/>" + v.getAgentlist().get(i).getClass().getSimpleName();
        }
        this.labels.get(6).setText("<html>Effects on player:" + out + "<br/><br/></html>");
    }

    /**
     * Render out the filed which the current virologist is standing on
     * Also render all the neighbours of this field
     */
    public void renderMap() {
        if (mapPanel != null) {
            mapPanel.revalidate();
            mapPanel.repaint();
        }
    }
}
