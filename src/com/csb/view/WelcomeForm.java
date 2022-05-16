package com.csb.view;

import com.csb.gameControl.GameController;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class WelcomeForm extends JFrame {

    //Big header title of the application
    private JLabel headerLabel;
    private ArrayList<String> names;

    public WelcomeForm() {
        names = new ArrayList<>();
        this.setSize(800, 400);
        this.setResizable(false);
        headerLabel = new JLabel("CSB - CSAK A BALÁZS");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);

        //A form to enter any number of names, all the names are displayed to the user
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        namePanel.add(headerLabel);
        this.add(namePanel, BorderLayout.CENTER);

        JButton addNameButton = new JButton("Add name");
        addNameButton.addActionListener(e -> {
            //Get name from the user
            String name = JOptionPane.showInputDialog(this, "Enter name of the player");
            if (name != null && !name.isEmpty()) {
                names.add(name);
                JLabel nameLabel = new JLabel("       Player: " + name);
                namePanel.add(nameLabel);
                namePanel.revalidate();
                namePanel.repaint();
            }
        });
        namePanel.add(addNameButton);

        Button gameButton = new Button("Start the game");

        gameButton.addActionListener(e -> {
            //If there are no names, show an error message
            if (names.isEmpty()) {
                PopUpView.showError("No names entered", "Please enter at least one name");
            }
            //If there are names, start the game
            else {
                this.setVisible(false);
                GameController.getInstance().initGame(names);
                GameController.getInstance().startGame();
            }
        });

        this.add(headerLabel, BorderLayout.NORTH);
        this.add(gameButton, BorderLayout.SOUTH);
    }
}
