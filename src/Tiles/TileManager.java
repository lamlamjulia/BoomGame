package Tiles;

import View.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    public GamePanel gp;
    public Tile tiles[];
    public int mapMatrix[][];
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[10];
        mapMatrix = new int[gp.maxCol][gp.maxRow];
        getTileImage();
        loadMap("/Tiles/map1.txt");
    }
    public void getTileImage()
    {
        try
        {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/Img/Tiles/bg0.png"));
            tiles[0].collisionOn = false;
            //collision?
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("../Img/Tiles/bg1.png"));
            tiles[1].collisionOn = false;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("../Img/Tiles/bg2.png"));
            tiles[2].collisionOn = true;

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("../Img/Tiles/bg3.png"));
            tiles[3].collisionOn = true;

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("../Img/Tiles/bg4.png"));
            tiles[4].collisionOn = true;

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("../Img/Tiles/bg5.png"));
            tiles[5].collisionOn = true;

            tiles[6] = new Tile();
            tiles[6].image = ImageIO.read(getClass().getResourceAsStream("../Img/Tiles/bg6.png"));
            tiles[6].collisionOn = true;
            System.out.println("tile img loaded");

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g)
    {
        int col = 0;
        int row = 0;
        while(col < gp.maxCol && row < gp.maxRow)
        {
            int tileNum = mapMatrix[col][row];
            int worldX = row*gp.tileSize;
            int worldY = col*gp.tileSize;
//            int screenX = worldX - gp.player.worldX + gp.player.screenX;
//            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            //havent caught corner cases
            g.drawImage(tiles[tileNum].image, worldX, worldY, gp.tileSize, gp.tileSize, null);

            col++;
            if(col == gp.maxCol)
            {
                col = 0;
                row++;
            }
        }
    }
    public void loadMap(String filePath)
    {
        try
        {
            InputStream input = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            int worldX = 0;
            int worldY = 0;
            while(worldY < gp.maxCol && worldX < gp.maxRow)
            {
                String line = br.readLine();
                while(worldY < gp.maxCol && worldX < gp.maxRow)
                {
                    String nums[] = line.split(" ");
                    int num =  Integer.parseInt(nums[worldY]);
                    mapMatrix[worldY][worldX] = num;
                    worldY++;
                }
                if(worldY == gp.maxCol)
                {
                    worldX++;
                    worldY = 0;
                }
                System.out.println("map loaded");
            }
            br.close();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
