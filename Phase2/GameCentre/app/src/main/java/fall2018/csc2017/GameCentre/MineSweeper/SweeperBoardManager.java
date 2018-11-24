package fall2018.csc2017.GameCentre.MineSweeper;

import fall2018.csc2017.GameCentre.BoardManager;

public class SweeperBoardManager extends BoardManager<SweeperTilesBoard> {

    public SweeperBoardManager(SweeperTilesBoard b){
        super(b);
    }
    @Override
    public int calculateScore(int moves) {
        return 0;
    }
    public void setBombToExploded(int row, int col) {
        this.getBoard().setBombToExploded(row, col);
    }
    public void setTileToNotFlaged(int row, int col) {
        this.getBoard().setTileToNotFlaged(row, col);
    }

    public void setTileToFlaged(int row, int col) {
        this.getBoard().setTileToFlaged(row, col);
    }

    public void setsBombdAround(int row, int col, int numberOfBombs) {
        this.getBoard().setBombsAround(row, col, numberOfBombs);
    }
}
