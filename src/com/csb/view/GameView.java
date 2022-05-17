package com.csb.view;

import com.csb.controller.VirologistController;
import com.csb.gameControl.GameController;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * The frame what determines the outline of the application
 */
public class GameView extends JFrame implements Serializable {

    private Container container;
    //private VirologistController virologistController;
    private VirologistView virologistView = null;
    private MapPanel mapPanel;
    private ArrayList<JLabel> labels;
    private JLabel nameLabel;
    private JLabel aminoLabel;
    private JLabel nucleicLabel;
    private JLabel inventoryLabel;
    private JLabel gencodeLabel;
    private JLabel createdLabel;
    private JLabel effectLabel;
    private JPanel dataPanel;

    public GameView() {
        init();
    }

    /**
     * initialize the frame
     */
    public void init() {
        setTitle("CSB - CSAK A BALAGE");

        this.setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        labels = new ArrayList<>();

        container = getContentPane();
        container.setLayout(new BorderLayout());

        initializeView(this);
        setVisible(true);
    }

    /**
     *
     * @param frame
     * Organise the view of the main frame
     */
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

    /**
     * Create the status labels for the given frame
     * @param frame
     */
    private void createStatusLabels(JFrame frame) {
        dataPanel = new JPanel();
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
        dataPanel.setPreferredSize(new Dimension(200, 500));
        dataPanel.setBorder(BorderFactory.createTitledBorder("Virologist data"));
        nameLabel = new JLabel("Name");

        /**
         * Add the panels
         */
        dataPanel.add(nameLabel);
        labels.add(nameLabel);
        aminoLabel = new JLabel("Amino acid:");

        dataPanel.add(aminoLabel);
        labels.add(aminoLabel);
        nucleicLabel = new JLabel("Nucleic acid:");

        dataPanel.add(nucleicLabel);
        labels.add(nucleicLabel);
        inventoryLabel = new JLabel("Inventory:");

        dataPanel.add(inventoryLabel);
        labels.add(inventoryLabel);
        gencodeLabel = new JLabel("Genetic codes:");

        dataPanel.add(gencodeLabel);
        labels.add(gencodeLabel);
        createdLabel = new JLabel("Created agents:");

        dataPanel.add(createdLabel);
        labels.add(createdLabel);
        effectLabel = new JLabel("Effects on player:");

        dataPanel.add(effectLabel);
        labels.add(effectLabel);

        frame.add(dataPanel, BorderLayout.WEST);

        /**
         * initialize the virologist view
         */
        virologistView = new VirologistView(labels, mapPanel);
    }

    /**
     *
     *Create the main menubar
     * @param frame
     */
    private void createMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu gameMenu = new JMenu("Game");
        JMenu actionMenu = new JMenu("Action");

        /**
         *
         * Add actionlisteners for the menu items
         */

        /**
         * Add actionlisteners for the load menu items
         */
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
            this.repaint();
        });
        fileMenu.add(loadItem);

        /**
         * Add actionlisteners for the save menu items
         */
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(x -> {
            String fileName = JOptionPane.showInputDialog(frame, "Enter file name (without extension)");
            try {
                GameController.getInstance().saveGame(fileName);
                PopUpView.showSuccess("Game saved successfully");
            } catch (IOException e) {
                PopUpView.showError("Error saving game", "Save Game - Error");
            }
            this.repaint();
        });
        fileMenu.add(saveItem);

        /**
         * Add actionlisteners for the exit menu items
         */
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(x -> {
            //Ask for confirmation
            int dialogResult = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
            this.repaint();
        });
        fileMenu.add(exitItem);

        /**
         * Add actionlisteners for the round ender menu item under the game menu
         */
        JMenuItem endRoundItem = new JMenuItem("End round");
        endRoundItem.addActionListener(x -> {
            GameController.getInstance().virologistController.roundEnd();
            this.repaint();
        });
        gameMenu.add(endRoundItem);

        /**
         * Add actionlisteners for the collect menu item under the action menu
         */
        JMenuItem collectItem = new JMenuItem("Collect");
        collectItem.addActionListener(x -> {
            GameController.getInstance().virologistController.collect();
            this.repaint();
        });
        actionMenu.add(collectItem);

        /**
         * Add actionlisteners for the move menu item under the action menu
         */
        JMenuItem moveItem = new JMenuItem("Move");
        moveItem.addActionListener(x -> {
            GameController.getInstance().virologistController.move();
            this.repaint();
        });
        actionMenu.add(moveItem);

        /**
         * Add actionlisteners for the steal menu item under the action menu
         */
        JMenuItem stealItem = new JMenuItem("Steal");
        stealItem.addActionListener(x -> {
            GameController.getInstance().virologistController.steal();
            this.repaint();
        });
        actionMenu.add(stealItem);

        /**
         * Add actionlisteners for the kill menu item under the action menu
         */
        JMenuItem killItem = new JMenuItem("Kill");
        killItem.addActionListener(x -> {
            GameController.getInstance().virologistController.murder();
            this.repaint();
        });
        actionMenu.add(killItem);

        /**
         * Add actionlisteners for the create agent menu item under the action menu
         */
        JMenuItem createAgentItem = new JMenuItem("Create agent");
        createAgentItem.addActionListener(x -> {
            GameController.getInstance().virologistController.createAgent();
            this.repaint();
        });
        actionMenu.add(createAgentItem);

        /**
         * Add actionlisteners for the use agent menu item under the action menu
         */
        JMenuItem useAgentsItem = new JMenuItem("Use agents");
        useAgentsItem.addActionListener(x -> {
            GameController.getInstance().virologistController.useAgent();
            this.repaint();
        });
        actionMenu.add(useAgentsItem);

        /**
         * Add actionlisteners for the discard menu item under the action menu
         */
        JMenuItem discardItem = new JMenuItem("Discard");
        discardItem.addActionListener(x -> {
            GameController.getInstance().virologistController.discard();
            this.repaint();
        });
        actionMenu.add(discardItem);

        /**
        Add the 3 main menus
         */
        menuBar.add(fileMenu);
        menuBar.add(gameMenu);
        menuBar.add(actionMenu);

        frame.setJMenuBar(menuBar);
    }

    /**
    getter for virologistview
     */
    public VirologistView getVirologistView() {
        return virologistView;
    }
}
