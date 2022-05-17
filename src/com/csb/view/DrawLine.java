package com.csb.view;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class DrawLine extends JPanel {

    /**coordinates**/
    private int x1, y1, x2, y2;

    public DrawLine(int x, int y, int xx, int yy) {
        x1 = x;
        y1 = y;
        x2 = xx;
        y2 = yy;
    }

    /**drawing a black line
    with the given coordinates**/
    @Override
    public void paint(Graphics g) {
        Graphics2D grap = (Graphics2D) g;
        grap.setColor(Color.BLACK);
        grap.drawLine(x1, y1, x2, y2);
    }
}
