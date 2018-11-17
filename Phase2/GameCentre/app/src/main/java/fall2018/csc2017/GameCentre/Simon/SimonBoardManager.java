package fall2018.csc2017.GameCentre.Simon;

import fall2018.csc2017.GameCentre.BoardManager;

public class SimonBoardManager extends BoardManager<SimonBoard> {

    private SimonBoard board;

    public SimonBoardManager(SimonBoard board) {
        this.board = board;
    }

    //TODO:implement this method
    @Override
    public int calculateScore(int moves) {
        return 0;
    }
}
