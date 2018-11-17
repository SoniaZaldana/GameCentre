package fall2018.csc2017.GameCentre.MineSweeper;

import java.util.List;

import fall2018.csc2017.GameCentre.Board;

public class SweeperTilesBoard extends Board<SweeperTile> {

    public SweeperTilesBoard(int dimension, List<SweeperTile> tiles) {
        super(dimension, tiles);
    }
}
