package View;

import Model.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private final int FPS = 60; //standard for a simple game
    Thread gameThread;
    Player player;
    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
        setFocusable(true); //for keyboard input
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run(){
        double drawInterval = (double) 1000000000/FPS;

        long startTime = System.nanoTime();
        long currentTime;
        double deltaTime = 0;
        int drawCounter = 0;
        long timer = 0;

        while(gameThread != null)
        {
            currentTime = System.nanoTime();
            deltaTime += (currentTime - startTime)/drawInterval;
            timer = currentTime - startTime;
            startTime = currentTime; //reset
            if(deltaTime >= 1)
            {
                update();
                repaint();
                deltaTime--;
                drawCounter++;
            }
            if(timer >= 1000000000)
            {
                System.out.println("FPS: " + drawCounter);
                drawCounter = 0;
                timer = 0;
            }
        }

    }
    public void update()
    {
        player.update();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.BLACK);
        g.drawString("Boom Online", 100, 100);
    }
}
