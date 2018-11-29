package fall2018.csc2017.GameCentre;

public class MoveTracker {
    /**
     * the number of moves
     */
    private int moves;

    /**
     * Instantiates move tracker object with 0 moves
     */
    public MoveTracker(){
        moves = 0;
    }

    /**
     * Instantiates move tracker object with a specific number of moves
     * @param moves
     */
    public MoveTracker(int moves){
        this.moves = moves;
    }

    /**
     * Sets the number of moves
     * @param score
     */
    public void setMoves(int score) {
        this.moves = score;
    }

    /**
     * Returns the number of moves
     * @return moves
     */
    public int getMoves() {
        return moves;
    }

    /**
     * Adds a specific number of moves
     * @param addThis - number of moves to be added
     */
    public void addMoves(int addThis) {
        moves +=addThis;
    }

}
