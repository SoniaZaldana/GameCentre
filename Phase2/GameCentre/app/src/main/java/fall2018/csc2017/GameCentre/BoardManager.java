package fall2018.csc2017.GameCentre;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
abstract class BoardManager<T extends Board> implements Serializable {
    List<Tile> tiles;
    int score;
    T board;

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

    public T getBoard(){
        return board;
    }
    public void setBoard(T board){
        this.board=board;
    }
    abstract int calculateScore(int moves);

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
    /**
     * @param position of touch on screen
     * @return the row number(Starts at 0).
     */
    public  int getRow(int position) {
        return position / board.getDimension();
    }

    /**
     * @param position of touch on screen
     * @return the column number(Starts at 0).
     */
    public int getCol(int position) {
        return position % board.getDimension();
    }
}
