package fall2018.csc2017.GameCentre.Simon;

import java.util.ArrayList;
import java.util.Random;

import fall2018.csc2017.GameCentre.BoardManager;
import fall2018.csc2017.GameCentre.Stack;

public class SimonBoardManager extends BoardManager<SimonTilesBoard> {

    private int undo;
    private Stack gameStack;

    public SimonBoardManager(SimonTilesBoard board, int undo) {
        super(board);
        this.undo = undo;
    }

    //TODO:implement this method
    @Override
    public int calculateScore(int moves) {
        return 0;
    }

    public int randomizer() {
        ArrayList<ArrayList<SimonTile>> simonList = this.getBoard().getAllTiles();
        Random rand = new Random();
        int index = rand.nextInt(simonList.size());
        return simonList.get(index);
    }
    //TODO instantiate stack
    // TODO method randomizer: returns random number from list of x numbers
    // TODO method populate stack(x elements) add x random elements

    public void populateStack() {

    }
    }
}
