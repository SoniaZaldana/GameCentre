package fall2018.csc2017.GameCentre.Simon;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc2017.GameCentre.CustomAdapter;
import fall2018.csc2017.GameCentre.GestureDetectGridViews.GestureDetectGridView;
import fall2018.csc2017.GameCentre.GestureDetectGridViews.GestureDetectGridViewShortPress;
import fall2018.csc2017.GameCentre.MovementControllers.MovementControllerSimplePress;
import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.SaveAndLoadBoardManager;

public class SimonGameActivity extends AppCompatActivity implements Observer {

    private SimonBoardManager boardManager;
    private MovementControllerSimplePress movementController;
    private ArrayList<Button> tileButtons;

    // Grid View and calculated column height and width based on device size
    private GestureDetectGridViewShortPress gridView;
    private static int columnWidth, columnHeight;

    //TODO: Implement this
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    void display(){
        // TODO: Change this to colour png not blank tile 16
        createTileGUI(ContextCompat.getDrawable(this, R.drawable.tile_16));
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    private void createTileButtons(Context context){
        SimonTilesBoard board = boardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != board.getDimension(); row++) {
            for (int col = 0; col != board.getDimension(); col++) {
               Button tmp = new Button(context);
               this.tileButtons.add(tmp);
            }
        }
    }

    private void createTileGUI(Drawable d){
        SimonTilesBoard board = boardManager.getBoard();
        int nextPos = 0;
        int numberOnTile;
        for (Button b: tileButtons){
            int row = nextPos / board.getDimension();
            int col = nextPos % board.getDimension();
            numberOnTile = board.getTile(row,col).getId();
            b.setText(String.valueOf(numberOnTile));
            // TODO: change this to blue tile instead of blank tile 16
            b.setBackground(ContextCompat.getDrawable(this, R.drawable.tile_16));
            nextPos++;
        }
    }




    @Override
    public void update(Observable o, Object arg) {

    }
}
