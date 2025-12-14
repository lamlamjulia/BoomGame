package Model;

import View.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    public int worldX,worldY;
    public int charWidth;
    public int charHeight;
    public int speed;
    BufferedImage up, down, left, right, bomb, dead;
    public String direction;
    public int bombCount;
    public boolean dropBomb;
    public boolean bombActive, bombExploded;
    public Rectangle solidArea;
    public boolean collisionOn;
    public int solidAreaDefaultX,solidAreaDefaultY;
    public String name;
    public int currentHealth;
    public int maxHealth;

}
