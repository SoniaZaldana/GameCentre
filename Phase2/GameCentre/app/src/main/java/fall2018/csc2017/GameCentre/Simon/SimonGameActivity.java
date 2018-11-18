package fall2018.csc2017.GameCentre.Simon;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.Button;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import fall2018.csc2017.GameCentre.CustomAdapter;
import fall2018.csc2017.GameCentre.GestureDetectGridViews.GestureDetectGridViewShortPress;
import fall2018.csc2017.GameCentre.MovementControllers.MovementControllerSimplePress;
import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.SaveAndLoadBoardManager;

public class SimonGameActivity extends AppCompatActivity implements Observer {

    private SimonBoardManager simonBoardManager;
    private MovementControllerSimplePress movementControllerSimon;
    private ArrayList<Button> tileButtons;

    // Grid View and calculated column height and width based on device size
    private GestureDetectGridViewShortPress gridView;
    private static int columnWidth, columnHeight;

    //TODO: Implement this
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        simonBoardManager = SaveAndLoadBoardManager.loadFromFile(this, SimonStartingActivity.SIMON_SAVE_FILENAME);
        setContentView(R.layout.activity_simon);

        // Add View to activity
        gridView = findViewById(R.id.grid);
        createTileButtons(this);
        gridView.setNumColumns(simonBoardManager.getBoard().getDimension());
        movementControllerSimon = new SimonMovementController(simonBoardManager);
        gridView.setMovementController(movementControllerSimon);
        simonBoardManager.getBoard().addObserver(this);
        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / simonBoardManager.getBoard().getDimension();
                        columnHeight = displayHeight / simonBoardManager.getBoard().getDimension();
                        display();
                    }
                });
    }

    void display(){
        // TODO: Change this to colour png not blank tile 16
        createTileGUI(ContextCompat.getDrawable(this, R.drawable.tile_16));
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    private void createTileButtons(Context context){
        SimonTilesBoard board = simonBoardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != board.getDimension(); row++) {
            for (int col = 0; col != board.getDimension(); col++) {
               Button tmp = new Button(context);
               this.tileButtons.add(tmp);
            }
        }
    }

    private void createTileGUI(Drawable d){
        SimonTilesBoard board = simonBoardManager.getBoard();
        int nextPos = 0;
        int numberOnTile;
        for (Button b: tileButtons){
            int row = nextPos / board.getDimension();
            int col = nextPos % board.getDimension();
            numberOnTile = board.getTile(row,col).getId();
            b.setText(String.valueOf(numberOnTile));
            // TODO: change this to blue tile instead of blank tile 1
            b.setBackground(ContextCompat.getDrawable(this, R.drawable.tile_1));
            nextPos++;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        display();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        SaveAndLoadBoardManager.saveToFile(this, SimonStartingActivity.SIMON_SAVE_FILENAME, simonBoardManager);
    }
}
