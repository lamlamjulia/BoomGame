package View;

import Helper.KeyHandler;
import Model.NPC;
import Model.Player;
import Tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    private final int FPS = 60; //standard for a simple game
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    public Player player = new Player(this, keyHandler);
    public NPC npc1 = new NPC(this, 1, 30, 30);
    public NPC npc2 = new NPC(this, 2, 100, 100);
    public NPC boss = new NPC(this, 3, 150, 150);
    public int scale = 3;
    public int tileSize = 16*scale;
    public int maxCol = 12;
    public int maxRow = 14;
    public int screenWidth = maxCol*tileSize;
    public int screenHeight = maxRow*tileSize;
    public int worldX = 0;
    public int worldY = 0;
    public TileManager tileManager = new TileManager(this);
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenHeight, screenWidth));
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
        //to be changed
        npc1.direction = "up";
        npc2.direction = "down";
        boss.direction = "left";
        npc1.update();
        npc2.update();
        boss.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2  = (Graphics2D) g;
        tileManager.draw(g2);
        player.draw(g2);
        npc1.draw(g2);
        npc2.draw(g2);
        boss.draw(g2);
        g.dispose();
    }
}
