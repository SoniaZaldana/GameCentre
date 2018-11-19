package fall2018.csc2017.GameCentre.Simon;

import java.util.ArrayList;
import java.util.Random;

import fall2018.csc2017.GameCentre.BoardManager;
import fall2018.csc2017.GameCentre.UndoStack;

public class SimonBoardManager extends BoardManager<SimonTilesBoard> {

    private int undo;
    private UndoStack gameUndoStack;

    public SimonBoardManager(SimonTilesBoard board, int undo) {
        super(board);
        this.undo = undo;
        // TODO instantiate gameUndoStack, but I am not sure about what size yet.
    }

    public UndoStack getGameUndoStack(){
        return this.gameUndoStack;
    }

    //TODO: evaluate if this way to calculate score will suffice
    @Override
    public int calculateScore(int moves) {
        return moves * 10;
    }

    // TODO: Randomizer should return a random tile from all tiles in order to display it
    ArrayList<SimonTile> randomizer() {
        ArrayList<ArrayList<SimonTile>> simonList = this.getBoard().getAllTiles();
        Random rand = new Random();
        int index = rand.nextInt(simonList.size());
        return simonList.get(index);
    }


    // TODO method populate stack. This method would take a single element x
    // TODO Then it would push this element onto the stack.
    void populateStack() {

    }
}

