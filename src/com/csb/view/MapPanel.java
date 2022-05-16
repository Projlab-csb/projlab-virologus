package com.csb.view;

import com.csb.fields.Field;
import com.csb.gameControl.GameController;
import com.csb.virologist.Virologist;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JPanel;

public class MapPanel extends JPanel {

    @Override
    public void paint(Graphics g) {
        Graphics2D graphic2d = (Graphics2D) g;
        int fieldSize = 75;
        int centerX = (getWidth() - fieldSize) / 2;
        int centerY = (getHeight() - fieldSize) / 2;

        //Draw current field on to the center of the map
        Field centerField = GameController.getInstance().virologistController.getVirologist().getField();

        //Space the other field on a circle around the center field
        double angle = 3 * Math.PI / 2;
        double angleStep = 2 * Math.PI / centerField.getNeighbors().size();
        Integer id = 0;

        for (int i = 0; i < centerField.getNeighbors().size(); i++) {
            Field field = centerField.getNeighbors().get(i);
            int x = centerX + (int) (2 * fieldSize * Math.cos(angle));
            int y = centerY + (int) (2 * fieldSize * Math.sin(angle));
            drawField(g, x, y, centerX, centerY, fieldSize, field, id);
            angle -= angleStep;
            id++;
        }

        drawField(g, centerX, centerY, centerX, centerY, fieldSize, centerField, null);
    }

    private void drawField(Graphics g, int x, int y, int centerX, int centerY, int size, Field field, Integer fieldId) {
        g.setColor(Color.BLACK);
        g.drawLine(x + size / 2, y + size / 2, centerX + size / 2, centerY + size / 2);
        //TODO: Use the proper colors
        switch (field.getType()) {
            case FIELD:
                g.setColor(Color.GREEN);
                break;
            case LAB:
                g.setColor(Color.BLUE);
                break;
            case SHELTER:
                g.setColor(Color.RED);
                break;
            case STORAGE:
                g.setColor(new Color(51, 204, 255));
                break;
        }
        g.fillOval(x, y, size, size);

        g.setColor(Color.BLACK);
        if (fieldId != null) {
            g.drawString(field.getType().name() + " (" + fieldId + ")", x, y);
        } else {
            g.drawString(field.getType().name(), x, y);
        }

        renderVirologists(g, x, y, size, field);
    }

    private void renderVirologists(Graphics g, int x, int y, int fieldSize, Field field) {
        for (int i = 0; i < field.getVirologists().size(); i++) {
            Virologist virologist = field.getVirologists().get(i);
            g.setColor(Color.BLACK);
            int lineHeight = (int) (fieldSize * 0.2);
            g.drawString(virologist.getName(), x, y + (i + 1) * lineHeight);
        }
    }
}