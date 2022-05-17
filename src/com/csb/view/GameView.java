package com.csb.view;

import com.csb.controller.VirologistController;
import com.csb.gameControl.GameController;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.*;

public class GameView extends JFrame implements Serializable {

    private Container container;
    //private VirologistController virologistController;
    private VirologistView virologistView = null;
    private MapPanel mapPanel;

    public GameView() {
        setTitle("CSB - CSAK A BALAGE");

        this.setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(new BorderLayout());

        initializeView(this);
        setVisible(true);
    }

    private void initializeView(JFrame frame) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        mapPanel = new MapPanel();
        mapPanel.setPreferredSize(new Dimension(500, 500));
        //mainPanel.setPreferredSize(new Dimension(500, 500));

        createMenuBar(frame);
        createStatusLabels(frame);

        frame.add(mapPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);
    }

    public void createStatusLabels(JFrame frame) {
        JPanel dataPanel = new JPanel();
        ArrayList<JLabel> labels = new ArrayList<>();
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
        dataPanel.setPreferredSize(new Dimension(200, 500));
        dataPanel.setBorder(BorderFactory.createTitledBorder("Virologist data"));
        JLabel nameLabel = new JLabel("Name");

        dataPanel.add(nameLabel);
        labels.add(nameLabel);
        JLabel aminoLabel = new JLabel("Amino acid:");

        dataPanel.add(aminoLabel);
        labels.add(aminoLabel);
        JLabel nucleicLabel = new JLabel("Nucleic acid:");

        dataPanel.add(nucleicLabel);
        labels.add(nucleicLabel);
        JLabel inventoryLabel = new JLabel("Inventory:");

        dataPanel.add(inventoryLabel);
        labels.add(inventoryLabel);
        JLabel gencodeLabel = new JLabel("Genetic codes:");

        dataPanel.add(gencodeLabel);
        labels.add(gencodeLabel);
        JLabel createdLabel = new JLabel("Created agents:");

        dataPanel.add(createdLabel);
        labels.add(createdLabel);
        JLabel effectLabel = new JLabel("Effects on player:");

        dataPanel.add(effectLabel);
        labels.add(effectLabel);
        /*
        JLabel nextPlayerLabel = new JLabel("Next player:");
        nextPlayerLabel.setFont(new Font("Serif", Font.BOLD, 20));
        dataPanel.add(nextPlayerLabel);
        labels.add(nextPlayerLabel);
        */

        frame.add(dataPanel, BorderLayout.WEST);

        virologistView = new VirologistView(labels, mapPanel);
    }

    private void createMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu gameMenu = new JMenu("Game");
        JMenu actionMenu = new JMenu("Action");

        //File Menu
        JMenuItem loadItem = new JMenuItem("Load");
        loadItem.addActionListener(x -> {
            JFileChooser FileChooserView = new JFileChooser();
            FileChooserView.setCurrentDirectory(new File("data" + File.separator + "saves"));
            var dialogResult = FileChooserView.showOpenDialog(this);
            if (dialogResult == JFileChooser.APPROVE_OPTION) {
                GameController.getInstance().loadGame(FileChooserView.getSelectedFile().getAbsolutePath());
                GameController.getInstance().startGame();
            }
        });
        fileMenu.add(loadItem);

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(x -> {
            String fileName = JOptionPane.showInputDialog(frame, "Enter file name (without extension)");
            try {
                GameController.getInstance().saveGame(fileName);
                PopUpView.showSuccess("Game saved successfully");
            } catch (IOException e) {
                PopUpView.showError("Error saving game", "Save Game - Error");
            }
        });
        fileMenu.add(saveItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(x -> {
            //Ask for confirmation
            int dialogResult = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);

        //Game Menu
        JMenuItem endRoundItem = new JMenuItem("End round");
        endRoundItem.addActionListener(x -> {
            GameController.getInstance().virologistController.roundEnd();
        });
        gameMenu.add(endRoundItem);

        //Action Menu
        JMenuItem collectItem = new JMenuItem("Collect");
        collectItem.addActionListener(x -> {
            GameController.getInstance().virologistController.collect();
        });
        actionMenu.add(collectItem);

        JMenuItem moveItem = new JMenuItem("Move");
        moveItem.addActionListener(x -> {
            GameController.getInstance().virologistController.move();
        });
        actionMenu.add(moveItem);

        JMenuItem stealItem = new JMenuItem("Steal");
        stealItem.addActionListener(x -> {
            GameController.getInstance().virologistController.steal();
        });
        actionMenu.add(stealItem);

        JMenuItem killItem = new JMenuItem("Kill");
        killItem.addActionListener(x -> {
            GameController.getInstance().virologistController.murder();
        });
        actionMenu.add(killItem);

        JMenuItem createAgentItem = new JMenuItem("Create agent");
        createAgentItem.addActionListener(x -> {
            GameController.getInstance().virologistController.createAgent();
        });
        actionMenu.add(createAgentItem);

        JMenuItem useAgentsItem = new JMenuItem("Use agents");
        useAgentsItem.addActionListener(x -> {
            GameController.getInstance().virologistController.useAgent();
        });
        actionMenu.add(useAgentsItem);

        JMenuItem discardItem = new JMenuItem("Discard");
        discardItem.addActionListener(x -> {
            GameController.getInstance().virologistController.discard();
        });
        actionMenu.add(discardItem);

        menuBar.add(fileMenu);
        menuBar.add(gameMenu);
        menuBar.add(actionMenu);

        frame.setJMenuBar(menuBar);
    }

    public VirologistView getVirologistView() {
        return virologistView;
    }
}
