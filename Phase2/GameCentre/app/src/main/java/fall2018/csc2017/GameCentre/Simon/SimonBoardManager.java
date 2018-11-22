package fall2018.csc2017.GameCentre.Simon;

import java.util.ArrayList;
import java.util.Random;
import fall2018.csc2017.GameCentre.BoardManager;

public class SimonBoardManager extends BoardManager<SimonTilesBoard>{
    /**
     * How many undos a user has in a game
     */
    private int undo;
    /**
     * The gameQueue for this instance of the game
     */
    private GameQueue<SimonTile> gameQueue;

    /**
     * Instantiates a SimonBoardManager object
     * @param board - the board for the game
     * @param undo - number of undos user has per game
     */
    public SimonBoardManager(SimonTilesBoard board, int undo) {
        super(board);
        this.undo = undo;
        this.gameQueue = new GameQueue<>();
    }

    /**
     * Returns the game queue for this object
     * @return
     */
    public GameQueue<SimonTile> getGameQueue(){
        return this.gameQueue;
    }

    @Override
    public int calculateScore(int moves) {
        return moves * 10;
    }

    // TODO: Randomizer should return a random tile from all tiles in order to display it
    // TODO: This needs to be fixed to replace a single tile and not a list
    SimonTile randomizer() {
        ArrayList<ArrayList<SimonTile>> simonList = this.getBoard().getAllTiles();
        Random rand = new Random();
        int index = rand.nextInt(simonList.size());
        //return simonList.get(index);

        //I am adding this code here so the function doesn't seem broken, but this needs fixing
        SimonTile simon = null;
        return simon;
    }
}

