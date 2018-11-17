package fall2018.csc2017.GameCentre.Simon;

import android.content.Context;

import fall2018.csc2017.GameCentre.MovementControllers.MovementControllerSimplePress;

public class SimonMovementController extends MovementControllerSimplePress {

    SimonMovementController(SimonBoardManager boardManager){
        setBoardManager(boardManager);
    }

    @Override
    public void processMove(Context context, int position) {
        SimonTile tile = getTileInPosition(position);
        //TODO: Check if this is the correct tile to have cliked based on stack
    }

    /**
     * Returns the SimonTile object in that position
     * @param position in board
     * @return SimonTile
     */
    private SimonTile getTileInPosition(int position) {
        int row = getBoardManager().getRow(position);
        int col = getBoardManager().getCol(position);
        SimonTile tile = (SimonTile) getBoardManager().getBoard().getTile(row,col);
        return tile;
    }
}
