package com.csb.view;

import com.csb.collectables.gencodes.Gencode;
import com.csb.gameControl.GameController;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The form what opens when the user starts the game
 *
 */

public class WelcomeForm extends JFrame {

    //Big header title of the application
    private JLabel headerLabel;
    private ArrayList<String> names;

    /**
     * WelcomeForm Constructor
     */
    public WelcomeForm() {
        names = new ArrayList<>();
        this.setSize(800, 400);
        this.setResizable(false);
        headerLabel = new JLabel("CSB - CSAK A BALAGE");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);

        /**
         * A form to enter any number of names, all the names are displayed to the user
         *
         */
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        namePanel.add(headerLabel);
        this.add(namePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addNameButton = new JButton("Add name");
        addNameButton.addActionListener(e -> {
            /**
             * Get name from the user
             */
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

        /**
         * Initializing the 2 new buttons
         */
        Button gameButton = new Button("Start new game");
        Button loadButton = new Button("Load game");

        /**
         * Add actionlisteners for the buttons
         */
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
            FileChooserView.setCurrentDirectory(new File("data" + File.separatorChar + "saves"));
            var dialogResult = FileChooserView.showOpenDialog(this);
            if (dialogResult == JFileChooser.APPROVE_OPTION) {
                GameController.getInstance().loadGame(FileChooserView.getSelectedFile().getAbsolutePath());
                GameController.getInstance().startGame();
                this.setVisible(false);
            }
        });

        /**
        Set the positions of the buttons
         */
        this.add(headerLabel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(loadButton, BorderLayout.EAST);
        buttonPanel.add(gameButton, BorderLayout.EAST);
    }
}
