package fall2018.csc2017.GameCentre.Simon;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc2017.GameCentre.GestureDetectGridViews.GestureDetectGridView;
import fall2018.csc2017.GameCentre.GestureDetectGridViews.GestureDetectGridViewShortPress;
import fall2018.csc2017.GameCentre.MovementControllers.MovementControllerSimplePress;

public class SimonActivity extends AppCompatActivity implements Observer {

    private SimonBoardManager boardManager;
    private MovementControllerSimplePress movementController;
    private ArrayList<Button> tileButtons;

    // Grid View and calculated column height and width based on device size
    private GestureDetectGridViewShortPress gridView;
    private static int columnWidth, columnHeight;

    private void createTileGUI(Drawable d){
        SimonTilesBoard board = boardManager.getBoard();
        int nextPos = 0;
        String tileColour;
        for (Button b: tileButtons){

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_simon);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
