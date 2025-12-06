package Model;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    public int worldX,worldY, screenX,screenY;
    public int speed;
    BufferedImage up, down, left, right, bomb;
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
