package Model;

import TileData.Tile;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class SimpleTileModel {
    private final int MX;
    private final int MY;

    private final boolean[][] waves;
    private final List<Integer>[][] entropies;

    private final int[][][] allPossibilities;

    public SimpleTileModel(int width, int height, Tile[] tiles) {
        MX = width;
        MY = height;
        waves = new boolean[MX][MY];
        entropies = new List[MX][MY];

        allPossibilities = new int[tiles.length][][];
        for (int i = 0; i < allPossibilities.length; i++) {
            allPossibilities[i] = tiles[i].compatibleTiles;
        }
        for (int x = 0; x < MX; x++) {
            for (int y = 0; y < MY; y++) {
                waves[x][y] = false;
                entropies[x][y] = new ArrayList<>();
                for (int i = 0; i < allPossibilities.length; i++) {
                    entropies[x][y].add(i);
                }
            }
        }
    }

    public int[][] Run()
    {
        int[][] wave = new int[MX][MY];
        int collapsedCount = 0;

        while(collapsedCount < MX * MY)
        {
            CollapseTile();
            collapsedCount++;
            PropagateWave();
        }

        for(int x = 0; x < MX; x++)
        {
            for (int y = 0; y < MY; y++)
            {
                wave[x][y] = entropies[x][y].get(0);
            }
        }
        return wave;
    }

    private void CollapseTile() {
        List<int[]> wavePossibilities = new ArrayList<>();
        for (int x = 0; x < MX; x++) {
            for (int y = 0; y < MY; y++) {
                if (!waves[x][y]) {
                    wavePossibilities.add(new int[]{x, y, entropies[x][y].size()});
                }
            }
        }
        wavePossibilities.sort(Comparator.comparingInt(value -> value[2]));
        wavePossibilities.removeIf(ints -> wavePossibilities.get(0)[2] < ints[2]);

        int random = new Random().nextInt(wavePossibilities.size());
        int x = wavePossibilities.get(random)[0];
        int y = wavePossibilities.get(random)[1];
        entropies[x][y] = List.of(entropies[x][y].get(new Random().nextInt(entropies[x][y].size())));
        waves[x][y] = true;
    }

    private void PropagateWave() {
        for (int x = 0; x < MX; x++)
        {
            for (int y = 0; y < MY; y++)
            {
                if (!waves[x][y])
                {
                    List<List<Integer>> entropy = new ArrayList<>();
                    entropy.add(entropies[x][y]);
                    if (0 < y) entropy.add(GetEntropy(x, y - 1, 2));
                    if (x < MX - 1) entropy.add(GetEntropy(x + 1, y, 3));
                    if (y < MY - 1) entropy.add(GetEntropy(x, y + 1, 0));
                    if (0 < x) entropy.add(GetEntropy(x - 1, y, 1));
                    entropies[x][y] = EvaluateEntropy(entropy);
                }
            }
        }
    }

    private List<Integer> GetEntropy(int x, int y, int side)
    {
        List<Integer> entropy = new ArrayList<>();
        if (waves[x][y]) for (int i: allPossibilities[entropies[x][y].get(0)][side]) entropy.add(i);
        else {
            for (int tile : entropies[x][y]) {
                    for(int compatibleTile: allPossibilities[tile][side]) {
                        if (!entropy.contains(compatibleTile)) {
                            entropy.add(compatibleTile);
                            if (entropy.size() == allPossibilities.length) {
                                return null;
                            }
                        }
                    }
                }
            }
        return entropy;
    }
    private List<Integer> EvaluateEntropy(List<List<Integer>> surroundingEntropy) {
        List<Integer> entropy = surroundingEntropy.get(0);

        for(int i = 1; i < surroundingEntropy.size(); i++) {
            if(surroundingEntropy.get(i) != null) {
                for(int j = 0; j < entropy.size(); j++) {
                    if(!surroundingEntropy.get(i).contains(entropy.get(j))) {
                        entropy.remove(entropy.get(j));
                        j--;
                    }
                }
            }
        }
        return entropy;
    }
}
