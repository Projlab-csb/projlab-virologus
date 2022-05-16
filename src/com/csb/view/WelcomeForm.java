package com.csb.view;

import com.csb.collectables.gencodes.Gencode;
import com.csb.gameControl.GameController;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

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

        Button gameButton = new Button("Start new game");
        Button loadButton = new Button("Load game");


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

        loadButton.addActionListener(e -> {
            JFileChooser FileChooserView = new JFileChooser();
            FileChooserView.setCurrentDirectory(new File("data\\saves"));
            var dialogResult = FileChooserView.showOpenDialog(this);
            if(dialogResult == JFileChooser.APPROVE_OPTION) {
                GameController.getInstance().loadGame(FileChooserView.getSelectedFile().getAbsolutePath());
                GameController.getInstance().startGame();
                this.setVisible(false);
            }
           });

        this.add(headerLabel, BorderLayout.NORTH);
        this.add(gameButton, BorderLayout.SOUTH);
        this.add(loadButton, BorderLayout.EAST);
    }
}
