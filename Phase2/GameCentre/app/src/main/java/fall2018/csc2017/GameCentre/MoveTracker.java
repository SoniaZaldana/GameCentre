package fall2018.csc2017.GameCentre;

public class MoveTracker {
    private int moves;
    public MoveTracker(){
        moves = 0;
    }
    public MoveTracker(int moves){
        this.moves = moves;
    }

    public void setMoves(int score) {
        this.moves = score;
    }

    public int getMoves() {
        return moves;
    }
    public void addMoves(int addThis) {
        moves +=addThis;
    }

}
