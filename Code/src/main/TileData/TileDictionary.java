package TileData;

import com.sun.source.tree.ReturnTree;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TileDictionary
{
    public HashMap<String, Tile[]> WangTileDir = new HashMap<>();

    private void InitWangTileDirectory() {
        String[][][] tileImgEdges = InitWangTileEdges();

        String folders = new File("").getAbsoluteFile().getParent() + "\\Tiles\\WangTiles";
        for (File folder : new File(folders).listFiles()) {
            String[][] tileEdges = getWangTileEdges(folder, tileImgEdges);
            //Init Tiles in proper order
            if(folder.getName().contains("2")) {
                Tile[] tiles = new Tile[16];
                for (int tile = 0; tile < 16; tile++) {
                    File file = GetFileByName(tile + ".png", folder.listFiles(), 16);
                    tiles[tile] = new Tile(file, tileEdges[tile]);
                }
                for (int i = 0; i < 16; i++) tiles[i].SetCompatibleTiles(tileEdges);
                WangTileDir.put(folder.getName(), tiles);
            }
            else if(folder.getName().contains("3")) {
                Tile[] tiles = new Tile[81];
                for (int tile = 0; tile < 81; tile++) {
                    File file = GetFileByName(tile + ".png", folder.listFiles(), 81);
                    tiles[tile] = new Tile(file, tileEdges[tile]);
                }
                for (int i = 0; i < 81; i++) tiles[i].SetCompatibleTiles(tileEdges);
                WangTileDir.put(folder.getName(), tiles);
            }
        }
    }

    private File GetFileByName(String name, File[] files, int range) {
        IntStream intStream = IntStream.range(0, range).filter(tile -> name.equals(files[tile].getName()));
        int tile = intStream.findFirst().getAsInt();
        return files[tile];
    }
    private String[][][] InitWangTileEdges() {
        String[][] WangTile2EdgeImgEdges = new String[16][4];
        String[][] WangTile2CornerImgEdges = new String[16][4];
        String[][] WangTile3EdgeImgEdges = new String[81][4];
        String[][] WangTile3CornerImgEdges = new String[81][4];
        for(int side = 0; side < 4; side++) {
            for(int tile = 0; tile < 16; tile++) {
                WangTile2EdgeImgEdges[tile][side] = Get1BitString(tile, side);
                WangTile2CornerImgEdges[tile][side] = Get1BitString(tile, (side+3)%4) + Get1BitString(tile, side);
            }
            for(int tile = 0; tile < 81; tile++) {
                WangTile3EdgeImgEdges[tile][side] = Get1TritString(tile, side);
                WangTile3CornerImgEdges[tile][side] = Get1TritString(tile, (side+3)%4) + Get1TritString(tile, side);
            }
        }
        return new String[][][]{WangTile2EdgeImgEdges, WangTile2CornerImgEdges, WangTile3EdgeImgEdges, WangTile3CornerImgEdges};
    }
    //<editor-fold desc="Gives String Value Img Edge of some Base at some bit place">
    private String Get1BitString(int val, int place) {
        boolean bit0 = GetBasePLaceVal(val, place, 2) == 1;
        return bit0 ? "B" : "A";
    }
    private String Get1TritString(int val, int place) {
        boolean trit0 = GetBasePLaceVal(val, place, 3) == 1;
        boolean trit1 = GetBasePLaceVal(val, place, 3) == 2;
        return !trit0&&!trit1 ? "A" : trit0&&!trit1 ? "B" : "C";
    }
    private int GetBasePLaceVal(int num, int place, int base) {
        return (num / (int) Math.pow(base, place))%base;
    }
    //</editor-fold>
    private String[][] getWangTileEdges(File file, String[][][] strings) {
        String[] tags = new String[]{"2-Edge", "2-Corner", "3-Edge", "3-Corner"};
        for(int tag = 0; tag < 4; tag++)
        {
            if(file.getName().contains(tags[tag])) return strings[tag];
        }
        return null;
    }
    public TileDictionary() {
            InitWangTileDirectory();
    }
}
