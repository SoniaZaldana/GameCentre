package fall2018.csc2017.GameCentre.Simon;

import fall2018.csc2017.GameCentre.BoardManager;

public class SimonBoardManager extends BoardManager<SimonTilesBoard> {

    private SimonTilesBoard board;

    public SimonBoardManager(SimonTilesBoard board) {
        this.board = board;
    }

    //TODO:implement this method
    @Override
    public int calculateScore(int moves) {
        return 0;
    }
}
