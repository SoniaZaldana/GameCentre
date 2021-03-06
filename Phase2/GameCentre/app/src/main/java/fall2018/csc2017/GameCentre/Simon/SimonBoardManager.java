package fall2018.csc2017.GameCentre.Simon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fall2018.csc2017.GameCentre.Board;
import fall2018.csc2017.GameCentre.BoardManager;
 public class SimonBoardManager extends BoardManager<Board<SimonTile>>{
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
    public SimonBoardManager(Board<SimonTile> board, int undo) {
        super(board);
        this.undo = undo;
        this.gameQueue = new GameQueue<>();
    }

    /**
     * Instantiates a SimonBoardManager using dimension and undo
     * @param dimension - dimension of the board
     * @param undo - max undo in the game
     */
    public SimonBoardManager(int dimension, int undo) {
        this.gameQueue = new GameQueue<>();
        this.undo = undo;
        List<SimonTile> tilesList = new ArrayList<>();
        final int numTiles = dimension * dimension;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tilesList.add(new SimonTile(tileNum));
        }
        Board<SimonTile> board = new Board<>(dimension, tilesList);
        setBoard(board);
    }

    /**
     * Return this board manager's undo
     * @return
     */
    public int getUndo(){
        return this.undo;
    }

    /**
     * Decrease the max number of undo by 1
     */
    void reduceUndo(){
        this.undo--;
    }

    /**
     *
     * @return Returns the game queue for this object
     */
    GameQueue<SimonTile> getGameQueue(){
        return this.gameQueue;
    }

    @Override
    public int calculateScore(int round) {
        int score = 0;
        for (int x = 0; x != round; x++) {
            score += x;
        }
        return score * (getBoard().getDimension() * 5);
    }

    /**
     * Returns a random tile from all tiles in order to display it ensuring random tiles are not
     * repeated as often
     * @return SimonTile
     */
    SimonTile randomizer() {
        //get the last tile in the gameQueue
        SimonTile newTile = randomizerHelper();
        if(!gameQueue.isEmpty()){
            SimonTile lastTile = gameQueue.get(gameQueue.getSize()-1);
            if(lastTile.getId() == newTile.getId()){
                //if the most recent tile and the new generated tile
                // are the same, generate another one.
                newTile = randomizer();
            }
        }
        return newTile;
    }
    /**
     * Returns a random tile from all tiles in order to display it
     * @return SimonTile
     */
    private SimonTile randomizerHelper(){
        ArrayList<ArrayList<SimonTile>> simonList = this.getBoard().getAllTiles();
        Random rand = new Random();
        int num = 0;
        for (ArrayList<SimonTile> tiles : simonList) {
            num = num + tiles.size();
        }
        int randNum = rand.nextInt(num);
        int index = 0;
        while (randNum >= simonList.get(index).size() ){
            randNum = randNum - simonList.get(index).size();
            index++;
        }
        return simonList.get(index).get(randNum);
    }
    SimonTile getTileInPosition(int position) {
        int row = getRow(position);
        int col = getCol(position);
        SimonTile tile = getBoard().getTile(row,col);
        return tile;
    }

}

