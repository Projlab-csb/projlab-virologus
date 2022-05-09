package com.csb.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawCircle extends JPanel {

    @Override
    public void paint(Graphics g) {
        Graphics2D graphic2d = (Graphics2D) g;
        graphic2d.setColor(Color.BLUE);
        graphic2d.fillRect(100, 50, 60, 80);
    }
}
