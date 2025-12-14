package Model;

import View.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class NPC extends Entity {
    public GamePanel gp;
    public NPC(GamePanel gp, int monsterNum, int X, int Y, String direction)
    {
        this.gp = gp;
        setWidthHeight(monsterNum);
        loadImg(monsterNum);
        this.speed = 5;
        this.worldX = X;
        this.worldY = Y;
        this.direction = direction;
    }
    public void loadImg(int monsterNum)
    {
        try
        {
            switch (monsterNum){
                case 1:
                    up = ImageIO.read(getClass().getResourceAsStream("/Img/NPC/quaivat1_up.png"));
                    down = ImageIO.read(getClass().getResourceAsStream("/Img/NPC/quaivat1_down.png"));
                    left = ImageIO.read(getClass().getResourceAsStream("/Img/NPC/quaivat1_left.png"));
                    right = ImageIO.read(getClass().getResourceAsStream("/Img/NPC/quaivat1_right.png"));
                    break;
                case 2:
                    up = ImageIO.read(getClass().getResourceAsStream("/Img/NPC/quaivat2_up.png"));
                    down = ImageIO.read(getClass().getResourceAsStream("/Img/NPC/quaivat2_down.png"));
                    left = ImageIO.read(getClass().getResourceAsStream("/Img/NPC/quaivat2_left.png"));
                    right = ImageIO.read(getClass().getResourceAsStream("/Img/NPC/quaivat2_right.png"));
                    break;
                case 3:
                    up = ImageIO.read(getClass().getResourceAsStream("/Img/NPC/quaivat3_up.png"));
                    down = ImageIO.read(getClass().getResourceAsStream("/Img/NPC/quaivat3_down.png"));
                    left = ImageIO.read(getClass().getResourceAsStream("/Img/NPC/quaivat3_left.png"));
                    right = ImageIO.read(getClass().getResourceAsStream("/Img/NPC/quaivat3_right.png"));
                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setWidthHeight(int mosterNum)
    {
        switch (mosterNum){
            case 1:
                this.charWidth = gp.tileSize;
                this.charHeight = gp.tileSize;
                break;
            case 2:
                this.charWidth = gp.tileSize;
                this.charHeight = gp.tileSize;
                break;
            case 3:
                this.charWidth = 5*gp.tileSize;
                this.charHeight = 5*gp.tileSize;
                break;
        }
    }
    public void update()
    {
        //collisionCheck, if hit something, change direction
        switch (direction)
        {
            case "up":
                worldY -= speed;
                break;
            case "down":
                worldY += speed;
                break;
            case "left":
                worldX -= speed;
                break;
            case "right":
                worldX += speed;
                break;
            case "still":
                break;
        }
        int right = gp.RIGHT + this.charWidth;
        int bot = gp.BOTTOM - 3*this.charHeight;
        worldX = Math.max(gp.LEFT, Math.min(worldX, right));
        worldY = Math.max(gp.TOP, Math.min(worldY, bot));
        //turn at corners, later would be checked with collision
        if(worldX == gp.LEFT || worldX == right || worldY == gp.TOP || worldY == bot)
        {
            if(direction.equals("up"))
                direction = "down";
            else if(direction.equals("down"))
                direction = "up";
            else if(direction.equals("left"))
                direction = "right";
            else if(direction.equals("right"))
                direction = "left";
            else direction = "still";
        }
    }
    public void draw(Graphics2D g2d)
    {
        BufferedImage img = null;
        g2d.drawImage(down, worldX, worldY, charWidth, charHeight, null);
    }

}
