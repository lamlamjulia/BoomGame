package Model;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    public int worldX,worldY, screenX,screenY;
    public int speed;
    BufferedImage up, down, left, right;
    public String direction;
    public Rectangle solidArea;
    public boolean collisionOn;
    public int solidAreaDefaultX,solidAreaDefaultY;
    public String name;
    public int currentHealth;
    public int maxHealth;
}
