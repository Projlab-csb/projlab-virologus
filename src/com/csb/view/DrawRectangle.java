package com.csb.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawRectangle extends JPanel {

    /**coordinates**/
    private int x, y, width, length;

    public DrawRectangle(int xc, int yc, int w, int l) {
        x = xc;
        y = yc;
        width = w;
        length = l;
    }


    /**drawing a red rectangle
     with the given coordinates**/
    @Override
    public void paint(Graphics g) {
        Graphics2D graphic2d = (Graphics2D) g;
        graphic2d.setColor(Color.RED);
        graphic2d.fillRect(0, 0, 60, 80);
    }
}
