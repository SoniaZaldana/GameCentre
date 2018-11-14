package fall2018.csc2017.GameCentre;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
abstract class BoardManager implements Serializable {
    List<Tile> tiles;
    int score;

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
    abstract int calculateScore(int moves);

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
