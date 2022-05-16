package com.csb.view;

import com.csb.fields.Field;
import com.csb.virologist.Virologist;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * TODO: Maybe this should be a renderable panel, and the view should hold all the virologist's dependent UI element
 */
public class VirologistView {

    private ArrayList<JLabel> labels = new ArrayList<JLabel>();

    public VirologistView(ArrayList<JLabel> label) {
        for (int i = 0; i < 7; i++) {
            this.labels.add(label.get(i));
        }
    }

    JLabel inventoryLabel = new JLabel("Inventory:");

    JLabel gencodeLabel = new JLabel("Genetic codes:");

    JLabel createdLabel = new JLabel("Created things:");

    JLabel effectLabel = new JLabel("Effects on player:");

    /**
     * Render label with the given name
     * @param v the virologist to be rendered
     */
    public void printVirologist(Virologist v) {
        String out = "";
        this.labels.get(0).setText("Name: " + v.getName());
        this.labels.get(1).setText("Amino Acid Level: " + v.getAminoAcid());
        this.labels.get(2).setText("Nucleic Acid Level: " + v.getNucleicAcid());

        for (int i = 0; i < v.getEquipments().size(); i++) {
            out += "\n" + v.getEquipments().get(i);
        }
        this.labels.get(3).setText("Inventory: " + out);

        out = "";
        for (int i = 0; i < v.getGencodes().size(); i++) {
            out += "\n" + v.getGencodes().get(i);
        }
        this.labels.get(4).setText("Genetic codes: " + out);

        out = "";
        for (int i = 0; i < v.getCreatedAgents().size(); i++) {
            out += "\n" + v.getCreatedAgents().get(i);
        }
        this.labels.get(5).setText("Created agents: " + out);

        out = "";
        for (int i = 0; i < v.getAgentlist().size(); i++) {
            out += "\n" + v.getAgentlist().get(i);
        }
        this.labels.get(6).setText("Effects on player:" + out);
    }

    /**
     * Render out the filed which the current virologist is standing on
     * Also render all the neighbours of this field
     * @param field the field to be rendered
     */
    public void renderMap(Field field) {}
}
