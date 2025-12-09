package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Bomb extends Entity {
    BufferedImage image, explosionImg;
    boolean bombActive, bombExploded;
    int bombCount;
    long bombStart;
    long explosionStart;
    public Bomb(boolean bombActive, boolean exploded,int bombCount) {
        this.bombActive = bombActive;
        this.bombCount = bombCount;
        this.bombExploded = exploded;
        setBombImage();
        setExplosionImage();
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
    public void setExplosionImage()
    {
        try
        {
            BufferedImage oriExplosionImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("../Img/Bomb/bombbang.png")));
            explosionImg = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
            explosionImg.getGraphics().drawImage(oriExplosionImg,0,0,null);

            System.out.println("loaded bomb explosion img");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
