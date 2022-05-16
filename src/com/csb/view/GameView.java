package com.csb.view;

import com.csb.gameControl.GameController;
import java.awt.*;
import javax.swing.*;

public class GameView extends JFrame {

    private Container container;
    //private VirologistController virologistController;
    private VirologistView virologistView;

    public GameView() {
        setTitle("CSB - Enterprise Virologist  Game");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(new BorderLayout());

        //This update is required to refresh the UI state, but will not be called like this.
        //After each user action, we can call this method in the specific view
        Button button = new Button("Render");
        button.addActionListener(e -> {
            GameController.getInstance().virologistController.updateView();
        });
        container.add(button, BorderLayout.SOUTH);

        initializeView(this);
        setVisible(true);
    }

    private void initializeView(JFrame frame) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel(new FlowLayout());

        mainPanel.add(leftPanel, BorderLayout.WEST);
        frame.add(mainPanel);

        createMenuBar(frame);
        createStatusLabels(frame);

        MapPanel d = new MapPanel();
        mainPanel.add(d);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);
    }

    private void createStatusLabels(JFrame frame) {
        JPanel dataPanel = new JPanel(new GridLayout(2, 7));
        dataPanel.setSize(1200, 500);
        dataPanel.setBorder(BorderFactory.createTitledBorder("Virologist data"));

        JLabel nameLabel = new JLabel("Name");
        dataPanel.add(nameLabel);

        JLabel matterLabel = new JLabel("Matter count:");
        dataPanel.add(matterLabel);
        JLabel aminoLabel = new JLabel("Amino acid:");
        dataPanel.add(aminoLabel);
        JLabel nucleicLabel = new JLabel("Nucleic acid:");
        dataPanel.add(nucleicLabel);
        JLabel inventoryLabel = new JLabel("Inventory:");
        dataPanel.add(inventoryLabel);
        JLabel gencodeLabel = new JLabel("Genetic codes:");
        dataPanel.add(gencodeLabel);
        JLabel createdLabel = new JLabel("Created things:");
        dataPanel.add(createdLabel);
        JLabel effectLabel = new JLabel("Effects on player:");
        dataPanel.add(effectLabel);

        JLabel nextPlayerLabel = new JLabel("Next player:");
        nextPlayerLabel.setFont(new Font("Serif", Font.BOLD, 20));
        dataPanel.add(nextPlayerLabel);

        frame.add(dataPanel, BorderLayout.WEST);
        virologistView = new VirologistView(nameLabel);
    }

    private void createMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu gameMenu = new JMenu("Game");
        JMenu actionMenu = new JMenu("Action");

        //File Menu
        JMenuItem loadItem = new JMenuItem("Load");
        loadItem.addActionListener(x -> {
            //GameController.getInstance().loadGame();
        });
        fileMenu.add(loadItem);

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(x -> {
            //GameController.getInstance().saveGame();
        });
        fileMenu.add(saveItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(x -> {});
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
