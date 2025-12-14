package Model;

import View.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
//tile 0125 can be destroyed
public class Bomb extends Entity {
    BufferedImage image, explosionImg;
    boolean bombActive, bombExploded;
    int bombCount;
    int bombX, bombY;
    long bombStart;
    long explosionStart;
    public int bombLength;
    public List<Point> explosionTiles = new ArrayList<>();
    GamePanel gp;

    public Bomb(boolean bombActive, boolean exploded, int bombCount, GamePanel gp) {
        this.gp = gp;
        this.bombActive = bombActive;
        this.bombCount = bombCount;
        this.bombExploded = exploded;
        this.charWidth = gp.tileSize;
        this.charHeight = gp.tileSize;
        setBombImage();
        setExplosionImage();
    }

    public void setBombImage()
    {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("../Img/Bomb/bomb.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setExplosionImage()
    {
        try
        {
            explosionImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("../Img/Bomb/bombbang.png")));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    public void calculateExplosionTiles()
    {
        explosionTiles.clear();
        int tileX = worldX/ gp.tileSize;
        int tileY = worldY/ gp.tileSize;
        explosionTiles.add(new Point(tileX,tileY));
    }
    public void addExplosionLine(int x, int y, int dx, int dy)
    {
        for(int i = 1; i <= bombLength; i++)
        {
            int nx = x + dx * i;
            int ny = y + dy * i;

        }
    }
}
