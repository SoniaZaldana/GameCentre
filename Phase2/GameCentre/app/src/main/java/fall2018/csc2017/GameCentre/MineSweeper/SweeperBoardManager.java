package fall2018.csc2017.GameCentre.MineSweeper;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

import fall2018.csc2017.GameCentre.BoardManager;

public class SweeperBoardManager extends BoardManager<SweeperTilesBoard> implements Serializable {
    /**
     * Whether or not there's a timer bomb active
     */
    private boolean bombActive = false;

    private int activeBombRow;


    private int activeBombCol;

    public SweeperBoardManager(SweeperTilesBoard b){
        super(b);
    }

    @Override
    public int calculateScore(int mines) {
        return ((100*mines) - getBoard().getTime());
    }

    public boolean isBombActive() {
        return bombActive;
    }


    public void setBombActive(boolean active){
        this.bombActive = active;
    }


    public void setBombToExploded(int row, int col) {
        this.getBoard().setBombToExploded(row, col);
    }
    public void setTileToNotFlagged(int row, int col) {
        this.getBoard().setTileToNotFlagged(row, col);
    }

    public void setTileToFlagged(int row, int col) {
        this.getBoard().setTileToFlagged(row, col);
    }

    public void setsBombsAround(int row, int col, int numberOfBombs) {
        this.getBoard().setBombsAround(row, col, numberOfBombs);
    }
    public SweeperTile getTileInPosition(int position) {
        int row = getRow(position);
        int col = getCol(position);
        SweeperTile tile = getBoard().getTile(row,col);
        return tile;
    }

    public int getActiveBombRow() {
        return activeBombRow;
    }

    public int getActiveBombCol() {
        return activeBombCol;
    }

    public void setActiveBombRow(int activeBombRow) {
        this.activeBombRow = activeBombRow;
    }

    public void setActiveBombCol(int activeBombCol) {
        this.activeBombCol = activeBombCol;
    }
}
