package Helper;

import Model.Entity;
import View.GamePanel;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }
    public void checkTile(Entity e){
        int entityLeftWorldX = e.worldX + e.solidArea.x;
        int entityRightWorldX = e.worldX + e.solidArea.x + e.solidArea.width;
        int entityTopWorldY = e.worldY + e.solidArea.y;
        int entityBottomWorldY = e.worldY + e.solidArea.y + e.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize ;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int mapMatrixRow;
        int mapMatrixCol;
        String direction = e.direction;
        switch (direction) {
            case "up":
                //predict where player would be after moving => solidArea.y subtract the speed (bc moving up = y+speed afterward), x doesnt change
                entityTopRow = (entityTopWorldY - e.speed)/gp.tileSize;
                mapMatrixRow = gp.tileManager.mapMatrix[entityTopRow][entityLeftCol];
                mapMatrixCol = gp.tileManager.mapMatrix[entityTopRow][entityRightCol];
                if(gp.tileManager.tiles[mapMatrixRow].collisionOn || gp.tileManager.tiles[mapMatrixCol].collisionOn){
                    e.collisionOn = true;
                }
                break;
            case "down":
                //solidArea.y + the speed (bc moving down = y - speed afterward), x doesnt change
                entityBottomRow = (entityBottomWorldY + e.speed)/gp.tileSize;
                mapMatrixRow = gp.tileManager.mapMatrix[entityBottomRow][entityLeftCol];
                mapMatrixCol = gp.tileManager.mapMatrix[entityBottomRow][entityRightCol];
                if(gp.tileManager.tiles[mapMatrixRow].collisionOn || gp.tileManager.tiles[mapMatrixCol].collisionOn){
                    e.collisionOn = true;
                }
                break;
            case "left":
                //solidArea.x + the speed (bc moving left = x-speed afterward), y doesnt change
                entityLeftCol = (entityLeftWorldX + e.speed)/gp.tileSize;
                mapMatrixRow = gp.tileManager.mapMatrix[entityTopRow][entityLeftCol];
                mapMatrixCol = gp.tileManager.mapMatrix[entityBottomRow][entityLeftCol];
                if(gp.tileManager.tiles[mapMatrixRow].collisionOn || gp.tileManager.tiles[mapMatrixCol].collisionOn){
                    e.collisionOn = true;
                }
                break;
            case "right":
                //solidArea.x - the speed (bc moving right = x+speed afterward), y doesnt change
                entityRightCol = (entityRightWorldX - e.speed)/gp.tileSize;
                mapMatrixRow = gp.tileManager.mapMatrix[entityTopRow][entityRightCol];
                mapMatrixCol = gp.tileManager.mapMatrix[entityBottomRow][entityRightCol];
                if(gp.tileManager.tiles[mapMatrixRow].collisionOn || gp.tileManager.tiles[mapMatrixCol].collisionOn){
                    e.collisionOn = true;
                }
                break;
        }
    }

}
