package fall2018.csc2017.GameCentre;

class SimonBoardManager extends BoardManager {

    private SimonBoard board;

    public SimonBoardManager(int dimension, SimonBoard board) {
        super(dimension);
        this.board = new SimonBoard(dimension, getTileList());
    }

    public SimonBoardManager(SimonBoard board) {
        this.board = board;
    }

    @Override
    SimonBoard getBoard() {
        return this.board;
    }

    //TODO: implement this method
    @Override
    boolean isGameFinished() {
        return false;
    }

    //TODO: implement this method
    @Override
    boolean isValidTap(int position) {
        return false;
    }

    //TODO: implement this method
    @Override
    void touchMove(int position) {

    }
    //TODO:implement this method
    @Override
    int calculateScore(int moves) {
        return 0;
    }
}
