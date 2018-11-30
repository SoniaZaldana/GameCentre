package fall2018.csc2017.GameCentre.Simon;

import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fall2018.csc2017.GameCentre.Board;
import fall2018.csc2017.GameCentre.Tile;

public class SimonTilesBoard extends Board<SimonTile> {
    /**
     * Instantiates a SimonTilesBoard object with a dimension and list of SimonTiles
     *
     * @param dimension - nxn dimension
     * @param tiles     - list of SimonTiles
     */

    SimonTilesBoard(int dimension, List<SimonTile> tiles) {
        super(dimension, tiles);
    }
//TODO implement it in the activity. Check if it is correct
    public void setColorsForAllTiles(List<Integer> listOfTileColors){
        int counter = 0;
        for(List<SimonTile> arr: getAllTiles()){
            for(SimonTile t: arr){
                t.setColor(listOfTileColors.remove(counter));
                counter+=1;
            }
        }
    }

}
