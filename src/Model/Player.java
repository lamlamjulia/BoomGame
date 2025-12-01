package Model;

import Helper.KeyHandler;
import View.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    public GamePanel gp; //to check collision
    public KeyHandler keyHandler;
    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        this.worldX = gp.screenWidth/2 -(gp.tileSize/2);;
        this.worldY = gp.screenHeight/2 -(gp.tileSize/2);;
        setDefault();
        getPlayerImg();
    }
    public void update()
    {
        //update img
        //if keyPress == "up" -> imgUp...
        if(keyHandler.downPressed || keyHandler.upPressed || keyHandler.leftPressed || keyHandler.rightPressed)
        {
            if(keyHandler.upPressed) {
                direction = "up";
            }
            else if(keyHandler.downPressed) {
                direction = "down";
            }
            else if(keyHandler.leftPressed) {
                direction = "left";
            }
            else if(keyHandler.rightPressed) {
                direction = "right";
            }
            switch (direction) {
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
        if(keyHandler.spacePressed && bombCount > 0 && !dropBomb) {
            dropBomb = true;
            bombCount--;
        }
    }
    public void setDefault()
    {
        direction = "down";
        dropBomb = false;
        speed = 5;
        bombCount = 2;
    }
    public void draw(Graphics2D g) {
        //add img to player
        BufferedImage img = null;
        long bombPlacedTime = 0;
        switch (direction) {
            case "up":
                img = up;
                break;
            case "down":
                img = down;
                break;
            case "left":
                img = left;
                break;
            case "right":
                img = right;
                break;
        }
        g.drawImage(img, worldX, worldY, gp.tileSize, gp.tileSize, null);
        if (dropBomb) {
            dropBomb = false;
            bombActive = true;
            bombPlacedTime = System.currentTimeMillis();
        }
        if(bombActive) {
            long elapsed = System.currentTimeMillis() - bombPlacedTime;
            if(elapsed > 1000) {
                bombActive  = false;
                g.drawImage(bomb, worldX, worldY, gp.tileSize, gp.tileSize, null);
            }

        }
    }

    public void getPlayerImg() {
        try
        {
            up = ImageIO.read(getClass().getResourceAsStream("/Img/Player/up.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/Img/Player/down.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/Img/Player/left.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/Img/Player/right.png"));
            bomb = ImageIO.read(getClass().getResourceAsStream("/Img/Bomb/bomb.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
