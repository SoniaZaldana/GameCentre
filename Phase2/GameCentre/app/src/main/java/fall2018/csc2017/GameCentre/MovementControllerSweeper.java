package fall2018.csc2017.GameCentre;

import android.content.Context;

public class MovementControllerSweeper extends MovementControllerComplexPress<SweeperBoardManager> {

    //TODO Change the boardManager
    MovementControllerSweeper(SweeperBoardManager boardManager) {
        setBoardManager(boardManager);

    }

    @Override
    public void processMove(Context context, int position, int e) {
        int row = getBoardManager().getRow(position);
        int col = getBoardManager().getCol(position);
        //TODO THIS IS TRICKY TO DO SO MUST DO DESIGN CHANGE
        //TODO because boardManager autaomatically generates Tile objects.
        // AND Board is automatically populated with Tile Objects
        /**
         * However every boardManager might generate his own.
         * Also make sure that
         */
        //SweeperTile t = getBoardManager().getBoard().getTile(row, col);
        int[] rowValues = {row - 1, row, row + 1};
        int[] colValues = {col - 1, col, col + 1};

        // If single  tap, then reveal what's under
        if (e == R.string.short_press) {

        }
        // If double tap, mark as having a bomb.
        if (e == R.string.long_press) {

        }
        // If it is a bomb
        // Display Bomb
        // End Game, Count Score, all of that.
        // If it is not a bomb
    }
    /**
     * What happens if the user chooses to display the tile
     */
    public void onTileDisplay(){
        // If it is a bomb
        // Display Bomb
        // End Game, Count Score, all of that.
        // If it is not a bomb
        displayTile(new Tile(0));
    }
    public void displayTile(Tile t){
        int bombCounter = 0;
        // iterate through all 8 tiles around, by doing row+k, col+k. Add +1 every time.
        // Check if the tile was already checked. If it wasn't, set a flag that it was. Maybe
        // set properties for tiles for : tileChecked(), hasBomb()
        if(bombCounter!=0){
            // Set button background to int bombCounter
        }
        else{
            // Set button background to "", empty.

            // Call the function on the rest.
//            displayTile(t1);
//            displayTile(t2);
//            displayTile(t3);
//            displayTile(t4);
//            displayTile(t5);
//            displayTile(t6);
//            displayTile(t7);
//            displayTile(t8);


        }
    }
}
