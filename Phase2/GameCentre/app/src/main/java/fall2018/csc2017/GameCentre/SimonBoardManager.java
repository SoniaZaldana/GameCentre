package fall2018.csc2017.GameCentre;

class SimonBoardManager extends BoardManager<SimonBoard> {

    private SimonBoard board;

    public SimonBoardManager(int dimension, SimonBoard board) {
//        super(dimension,board );
//        this.board = new SimonBoard(dimension, getTileList());
    }

    public SimonBoardManager(SimonBoard board) {
        this.board = board;
    }

    //TODO:implement this method
    @Override
    int calculateScore(int moves) {
        return 0;
    }
}
