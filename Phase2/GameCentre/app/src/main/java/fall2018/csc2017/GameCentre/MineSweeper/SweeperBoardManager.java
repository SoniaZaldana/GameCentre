package fall2018.csc2017.GameCentre.MineSweeper;

import java.io.Serializable;

import fall2018.csc2017.GameCentre.BoardManager;

/**
 * The manager for the minesweeper board
 */
public class SweeperBoardManager extends BoardManager<SweeperTilesBoard> implements Serializable {
    /**
     * Whether or not there's a timer bomb active
     */
    private boolean bombActive = false;

    /**
     * The horizontal location of the time bomb that was activated
     */
    private int activeBombRow;

    /**
     *  The vertical location of the time bomb that was activated
     */
    private int activeBombCol;

    public SweeperBoardManager(SweeperTilesBoard b) {
        super(b);
    }

    /**
     * Returns the score of the game when you win
     *
     * @param mines The number of mines in the game
     * @return The score you got from the game
     */
    @Override
    public int calculateScore(int mines) {
        return ((100 * mines) - getBoard().getTime());
    }

    /**
     * Getter for isBombActive
     *
     * @return true if time bomb is active, false otherwise
     */
    public boolean isBombActive() {
        return bombActive;
    }

    /**
     * Setter for isBombActive.
     *
     * @param active bomb state
     */
    public void setBombActive(boolean active) {
        this.bombActive = active;
    }

    /**
     * Sets the bomb state to true for exploded
     *
     * @param row of the bomb
     * @param col of the bomb
     */
    public void setBombToExploded(int row, int col) {
        this.getBoard().setBombToExploded(row, col);
    }

    /**
     * Sets whether or not the tile is flagged
     *
     * @param row of the non-flagged tile
     * @param col of the non-flagged tile
     */
    public void setTileToNotFlagged(int row, int col) {
        this.getBoard().setTileToNotFlagged(row, col);
    }

    /**
     * Sets the tile to flagged
     *
     * @param row of the newly flagged tile
     * @param col of the newly flagged tile
     */
    public void setTileToFlagged(int row, int col) {
        this.getBoard().setTileToFlagged(row, col);
    }

    /**
     * Sets the number displayed of the number of bombs around the tile
     *
     * @param row of the target tile
     * @param col of the target tile
     * @param numberOfBombs around the tile
     */
    public void setsBombsAround(int row, int col, int numberOfBombs) {
        this.getBoard().setBombsAround(row, col, numberOfBombs);
    }

    public SweeperTile getTileInPosition(int position) {
        int row = getRow(position);
        int col = getCol(position);
        SweeperTile tile = getBoard().getTile(row, col);
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
