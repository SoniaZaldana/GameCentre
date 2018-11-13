package fall2018.csc2017.GameCentre;

public class MineSweeperActivity {
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
