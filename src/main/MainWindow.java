package main;

import View.GamePanel;

import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Boom Online");

        setVisible(true);


        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
        pack();
    }
}
