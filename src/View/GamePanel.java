package View;

import Helper.KeyHandler;
import Model.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {

    private final int FPS = 60; //standard for a simple game
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(this, keyHandler);
    public int scale = 3;
    public int tileSize = 16*scale;
    public int maxCol = 16;
    public int maxRow = 16;
    public int screenWidth = maxCol*tileSize;
    public int screenHeight = maxRow*tileSize;
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.addKeyListener(keyHandler);
        this.setFocusable(true); //for keyboard input

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
                System.out.println("updated");
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2  = (Graphics2D) g;
        player.draw(g2);
        g.dispose();
    }
}
