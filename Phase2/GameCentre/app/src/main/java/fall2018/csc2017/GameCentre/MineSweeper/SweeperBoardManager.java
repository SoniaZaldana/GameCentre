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

    /**
     * Instantiates a sweeper board manager
     * @param b
     */
    SweeperBoardManager(SweeperTilesBoard b){
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
     * Returns whether the timer bomb is active
     * @return
     */
    boolean isBombActive() {
        return bombActive;
    }

    /**
     * Sets the bomb timer active
     * @param active
     */
    void setBombActive(boolean active){
        this.bombActive = active;
    }

    /**
     * Sets the bomb that will explode
     * @param row - row the bomb is in the board
     * @param col - col the bomb is in the board
     */
    void setBombToExploded(int row, int col) {
        this.getBoard().setBombToExploded(row, col);
    }

    /**
     * Sets a tile to flagged
     * @param row - row in the board
     * @param col - column in the board
     * @param flag
     */
    void setTileToFlagged(int row, int col, boolean flag) {
        this.getBoard().setTileToFlagged(row, col, flag);
    }

    /**
     * Sets the number displayed of the number of bombs around the tile
     *
     * @param row of the target tile
     * @param col of the target tile
     * @param numberOfBombs around the tile
     */
    void setsBombsAround(int row, int col, int numberOfBombs) {
        this.getBoard().setBombsAround(row, col, numberOfBombs);
    }

    SweeperTile getTileInPosition(int position) {
        int row = getRow(position);
        int col = getCol(position);
        SweeperTile tile = getBoard().getTile(row, col);
        return tile;
    }

    int getActiveBombRow() {
        return activeBombRow;
    }

    int getActiveBombCol() {
        return activeBombCol;
    }

    void setActiveBombRow(int activeBombRow) {
        this.activeBombRow = activeBombRow;
    }

    void setActiveBombCol(int activeBombCol) {
        this.activeBombCol = activeBombCol;
    }
}
