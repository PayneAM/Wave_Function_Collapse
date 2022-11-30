import TileData.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {
    @Test
    void TestCompatibleTilesNoResult() {
        Tile tile = new Tile(new File(""), new String[]{"A", "B", "C", "D"});
        int[][] expectedResult = new int[][]{
                {},
                {},
                {},
                {}
        };
        String[][] givenEdges = new String[][]{
                {"A", "B", "C", "D"},
                {"A", "B", "C", "D"},
                {"A", "B", "C", "D"},
                {"A", "B", "C", "D"},
        };
        tile.SetCompatibleTiles(givenEdges);
        Assertions.assertArrayEquals(expectedResult, tile.compatibleTiles);
    }
    @Test
    void TestCompatibleTilesOneResult() {
        Tile tile = new Tile(new File(""), new String[]{"A", "B", "C", "D"});
        int[][] expectedResult = new int[][]{
                {0},
                {},
                {},
                {}
        };
        String[][] givenEdges = new String[][]{
                {"A", "B", "A", "D"},
                {"A", "B", "C", "D"},
                {"A", "B", "C", "D"},
                {"A", "B", "C", "D"},
        };
        tile.SetCompatibleTiles(givenEdges);
        Assertions.assertArrayEquals(expectedResult, tile.compatibleTiles);
    }
    @Test
    void TestCompatibleTilesManyResults() {
        Tile tile = new Tile(new File(""), new String[]{"A", "B", "C", "D"});
        int[][] expectedResult = new int[][]{
                {0},
                {1},
                {2},
                {3}
        };
        String[][] givenEdges = new String[][]{
                {"A", "B", "A", "D"},
                {"A", "B", "C", "B"},
                {"C", "B", "C", "D"},
                {"A", "D", "C", "D"},
        };
        tile.SetCompatibleTiles(givenEdges);
        Assertions.assertArrayEquals(expectedResult, tile.compatibleTiles);
    }
}