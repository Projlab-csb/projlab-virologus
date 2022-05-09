package com.csb.view;

import com.csb.controller.VirologistController;
import com.csb.gameControl.GameController;
import java.awt.*;
import javax.swing.*;

public class GameView extends JFrame {

    private Container container;
    //private VirologistController virologistController;
    private VirologistView virologistView;

    public GameView() {
        setTitle("CSB - Virologist Enterprise Game");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(new BorderLayout());

        Label label = new Label("Virologist Demo Name");
        label.setFont(new Font("Arial", Font.BOLD, 30));
        container.add(label, BorderLayout.NORTH);

        //This update is required to refresh the UI state, but will not be called like this.
        //After each user action, we can call this method in the specific view
        Button button = new Button("Render");
        button.addActionListener(e -> {
            GameController.getInstance().virologistController.updateView();
        });
        container.add(button, BorderLayout.SOUTH);

        virologistView = new VirologistView(label);

        initializeView();
        setVisible(true);
    }

    private void initializeView(){

            JFrame frame = new JFrame("CSB Enterprise Virologist Game");
            JPanel mainPanel = new JPanel(new BorderLayout());
            JPanel leftPanel = new JPanel(new FlowLayout());

            mainPanel.add(leftPanel, BorderLayout.WEST);
            frame.add(mainPanel);

            createMenuBar(frame);

            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
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
        exitItem.addActionListener(x -> {
            ;
        });
        fileMenu.add(exitItem);

        //Game Menu
        JMenuItem endRoundItem = new JMenuItem("End round");
        endRoundItem.addActionListener(x -> {

        });
        gameMenu.add(endRoundItem);

        //Action Menu
        JMenuItem collectItem = new JMenuItem("Collect");
        collectItem.addActionListener(x -> {

        });
        actionMenu.add(collectItem);
        
        JMenuItem stealItem = new JMenuItem("Steal");
        stealItem.addActionListener(x -> {

        });
        actionMenu.add(stealItem);

        JMenuItem killItem = new JMenuItem("Kill");
        killItem.addActionListener(x -> {

        });
        actionMenu.add(killItem);

        JMenuItem createAgentItem = new JMenuItem("Create agent");
        createAgentItem.addActionListener(x -> {

        });
        actionMenu.add(createAgentItem);

        JMenuItem useAgentsItem = new JMenuItem("Use agents");
        useAgentsItem.addActionListener(x -> {

        });
        actionMenu.add(useAgentsItem);

        JMenuItem discardItem = new JMenuItem("Discard");
        discardItem.addActionListener(x -> {

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
