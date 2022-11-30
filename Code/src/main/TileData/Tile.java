package TileData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Tile {
    public final File imgFile;
    public final String[] imgEdges;
    public int[][] compatibleTiles = new int[4][];

    public Tile(File imgFile, String[] imgEdges)
    {
        this.imgFile = imgFile;
        this.imgEdges = imgEdges;
    }

    public void SetCompatibleTiles(String[][] tiles)
    {
        for(int side = 0; side < 4; side++) {
            List<Integer> compatibleTilesList = new ArrayList<>();
            for(int tile = 0; tile < tiles.length; tile++) {
                String oppositeSide = new StringBuilder(tiles[tile][(side+2)%4]).reverse().toString();
                if(imgEdges[side].equals(oppositeSide)) compatibleTilesList.add(tile);
            }
            compatibleTiles[side] = compatibleTilesList.stream().mapToInt(Integer::intValue).toArray();
        }
    }
}
