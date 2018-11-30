package fall2018.csc2017.GameCentre;

import java.io.Serializable;

/**
 *
 */
public abstract class BoardManager<T extends Board> implements Serializable {
    /**
     * the score for this board manager
     */
    private int score;
    /**
     * the board this board manager handles
     */
    private T board;

    /**
     * Instantiates a board manager object with a board
     * @param board
     */
    public BoardManager(T board) {
        this.board = board;
    }

    /**
     * Default constructor for board manager
     */
    public BoardManager() {
    }

    /**
     * Returns the board associated with this board manager
     * @return board
     */
    public T getBoard(){
        return board;
    }

    /**
     * Sets the board in this board manager
     * @param board
     */
    public void setBoard(T board){
        this.board=board;
    }

    /**
     * Calculates the score for the board
     * @param moves - number of moves made
     * @return integer score
     */
    public abstract int calculateScore(int moves);

    /**
     * Sets the score for this board manager
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets the score associated with this board manager
     * @return
     */
    public int getScore() {
        return score;
    }
    /**
     * @param position of touch on screen
     * @return the row number(Starts at 0).
     */
    public  int getRow(int position) {
        return position / board.getDimension();
    }

     /**
     * get the position, given a row and a column
     * @param row
     * @param col
     * @return
     */
    public int getPosition(int row, int col){
        return row* board.getDimension() + col;
    }

    /**
     * @param position of touch on screen
     * @return the column number(Starts at 0).
     */
    public int getCol(int position) {
        return position % board.getDimension();
    }
}
