package Model;

import View.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class NPC extends Entity {
    public GamePanel gp;
    public NPC(GamePanel gp, int monsterNum, int X, int Y)
    {
        loadImg(monsterNum);
        this.gp = gp;
        //to be changed
        this.worldX = X;
        this.worldY = Y;
        this.speed = 5;
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
        }
    }
    public void draw(Graphics2D g2d)
    {
        BufferedImage img = null;
        g2d.drawImage(up, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }

}
