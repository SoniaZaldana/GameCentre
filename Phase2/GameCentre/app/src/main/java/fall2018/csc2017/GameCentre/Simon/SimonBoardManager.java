package fall2018.csc2017.GameCentre.Simon;

import fall2018.csc2017.GameCentre.BoardManager;

public class SimonBoardManager extends BoardManager<SimonTilesBoard> {

    private int undo;

    public SimonBoardManager(SimonTilesBoard board, int undo) {
        super(board);
        this.undo = undo;
    }

    //TODO:implement this method
    @Override
    public int calculateScore(int moves) {
        return 0;
    }

    //TODO instantiate stack
    // TODO method randomizer: returns random number from list of x numbers
    // TODO method populate stack(x elements) add x random elements

}
