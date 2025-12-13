package Model;

import View.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class NPC extends Entity {
    public GamePanel gp;
    BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public NPC(GamePanel gp, int monsterNum, int X, int Y)
    {
        loadImg(monsterNum);
        this.gp = gp;
        this.worldX = X;
        this.worldY = Y;
    }
    public void loadImg(int monsterNum)
    {
        try
        {
            switch (monsterNum){
                case 1:
                    up1 = ImageIO.read(getClass().getResourceAsStream("/Img/NPC/quaivat1_up.png"));
                    down1 = ImageIO.read(getClass().getResourceAsStream("/Img/NPC/quaivat1_down.png"));
                    left1 = ImageIO.read(getClass().getResourceAsStream("/Img/NPC/quaivat1_left.png"));
                    right1 = ImageIO.read(getClass().getResourceAsStream("/Img/NPC/quaivat1_right.png"));
                    break;
                case 2:
                    up2 = ImageIO.read(getClass().getResourceAsStream("/Img/NPC/quaivat2_up.png"));
                    down2 = ImageIO.read(getClass().getResourceAsStream("/Img/NPC/quaivat2_down.png"));
                    left2 = ImageIO.read(getClass().getResourceAsStream("/Img/NPC/quaivat2_left.png"));
                    right2 = ImageIO.read(getClass().getResourceAsStream("/Img/NPC/quaivat2_right.png"));
                    break;
                case 3:
                    up3 = ImageIO.read(getClass().getResourceAsStream("/Img/NPC/quaivat3_up.png"));
                    down3 = ImageIO.read(getClass().getResourceAsStream("/Img/NPC/quaivat3_down.png"));
                    left3 = ImageIO.read(getClass().getResourceAsStream("/Img/NPC/quaivat3_left.png"));
                    right3 = ImageIO.read(getClass().getResourceAsStream("/Img/NPC/quaivat3_right.png"));
                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void draw(Graphics2D g2d)
    {
        BufferedImage img = null;
    }
}
