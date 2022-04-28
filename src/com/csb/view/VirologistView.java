package com.csb.view;

import com.csb.fields.Field;
import java.awt.*;

/**
 * TODO: Maybe this should be a renderable panel, and the view should hold all the virologist's dependent UI element
 */
public class VirologistView {

    private Label nameLabel;

    public VirologistView(Label nameLabel) {
        this.nameLabel = nameLabel;
    }

    /**
     * Render label with the given name
     * @param name the name to be rendered
     */
    public void printName(String name) {
        nameLabel.setText(name);
    }

    /**
     * Render out the filed which the current virologist is standing on
     * Also render all the neighbours of this field
     * @param field the field to be rendered
     */
    public void renderMap(Field field) {}
}
