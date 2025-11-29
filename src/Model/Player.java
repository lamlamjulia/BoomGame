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
        this.screenX = gp.screenWidth/2 -(gp.tileSize/2);;
        this.screenY = gp.screenHeight/2 -(gp.tileSize/2);;
        setDefault();
        getPlayerImg();
    }
    public void update()
    {
        System.out.println("updated player");
        //update img
        //if keyPress == "up" -> imgUp...
        if(keyHandler.downPressed || keyHandler.upPressed || keyHandler.leftPressed || keyHandler.rightPressed)
        {
            if(keyHandler.upPressed) {
                direction = "up";
                System.out.println("up pressed");
            }
            else if(keyHandler.downPressed) {
                direction = "down";
                System.out.println("down pressed");
            }
            else if(keyHandler.leftPressed) {
                direction = "left";
                System.out.println("left pressed");
            }
            else if(keyHandler.rightPressed) {
                direction = "right";
                System.out.println("right pressed");
            }
            switch (direction) {
                case "up":
                    screenY -= speed;
                    break;
                case "down":
                    screenY += speed;
                    break;
                case "left":
                    screenX -= speed;
                    break;
                case "right":
                    screenX += speed;
                    break;
            }
        }

    }
    public void setDefault()
    {
        direction = "down";
        speed = 5;
    }
    public void draw(Graphics2D g){
        //add img to player
        BufferedImage img = null;
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
        g.drawImage(img, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
    public void getPlayerImg() {
        try
        {
            up = ImageIO.read(getClass().getResourceAsStream("/Img/Player/up.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/Img/Player/down.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/Img/Player/left.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/Img/Player/right.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
