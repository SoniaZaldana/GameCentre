package fall2018.csc2017.GameCentre;

import java.util.ArrayList;
import java.util.List;

abstract class BoardManager {
    List<Tile> tiles;

    public BoardManager(int dimension) {
        tiles = new ArrayList<>();
        final int numTiles = dimension * dimension;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum + 1));
        }
    }

    BoardManager() {
    }

    List<Tile> getTileList() {
        return this.tiles;
    }

    abstract Board getBoard();

    abstract boolean isGameFinished();

    abstract boolean isValidTap(int position);

    abstract void touchMove(int position);

    abstract int calculateScore(int moves);
}
