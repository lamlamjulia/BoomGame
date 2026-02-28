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
    public Bomb bomb;
    public int bombX, bombY;
    public int bombExpX, bombExpY;
    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.charHeight = 56;
        this.charWidth = 45;
        this.keyHandler = keyHandler;
        this.worldX = 100;;
        this.worldY = 100;;
        setDefault();
        getPlayerImg();
        this.bomb = new Bomb(false, false, 2, gp);
        this.solidArea = new Rectangle(0,35,42,15);

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

            //check collision
            collisionOn = false;
            gp.collisionChecker.checkTile(this);
            //if collision is false -> move
            if(!collisionOn){
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
            worldX = Math.max(gp.LEFT, Math.min(worldX, gp.RIGHT+this.charWidth));
            worldY = Math.max(gp.TOP, Math.min(worldY, gp.BOTTOM-3*this.charHeight));
        }
        if(keyHandler.spacePressed
                //&& bombCount > 0
                && !bombActive) {
            bombActive = true;
            bomb.bombStart = System.currentTimeMillis();
            bombX = worldX - speed;
            bombY = worldY  - speed;
            //bombCount--;
        }
        if(bombActive) {
            long elapsed = System.currentTimeMillis() - bomb.bombStart;

            if(elapsed > 3000) { //3s
                bombActive  = false;
                bombExploded = true;
                bomb.explosionStart = System.currentTimeMillis();
            }
        }
        if(bombExploded)
        {
            long elapsed = System.currentTimeMillis() - bomb.explosionStart;
            if(elapsed >500 )
            {
                bombExploded = false;
            }
        }
    }
    public void setDefault()
    {
        direction = "down";
        dropBomb = false;
        speed = 5;
        //bombCount = 2;
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
        g.drawImage(img, worldX, worldY, charWidth, charHeight, null);
        if(bombActive){
            g.drawImage(bomb.image, bombX, bombY, gp.tileSize, gp.tileSize, null);
        }
        if(bombExploded)
        {
            bombExpX = bombX- gp.tileSize;
            bombExpY = bombY - gp.tileSize;
            g.drawImage(bomb.explosionImg, bombExpX, bombExpY, 3*gp.tileSize, 3*gp.tileSize, null);
        }
    }

    public void getPlayerImg() {
        try
        {
            up = ImageIO.read(getClass().getResourceAsStream("/Img/Player/up.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/Img/Player/down.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/Img/Player/left.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/Img/Player/right.png"));
            dead = ImageIO.read(getClass().getResourceAsStream("/Img/Player/dead.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
