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

        setVisible(true);
    }

    public VirologistView getVirologistView() {
        return virologistView;
    }
}
