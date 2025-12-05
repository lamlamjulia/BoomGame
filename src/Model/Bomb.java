package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bomb extends Entity {
    BufferedImage image;
    boolean bombActive;
    int bombCount;
    long bombStart;
    public Bomb(boolean bombActive, int bombCount) {
        this.bombActive = bombActive;
        this.bombCount = bombCount;
        setBombImage();
    }

    public void setBombImage()
    {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("../Img/Bomb/bomb.png"));
            System.out.println("loaded bomb img");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
