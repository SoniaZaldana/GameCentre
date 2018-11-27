package fall2018.csc2017.GameCentre.Simon;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;
import fall2018.csc2017.GameCentre.CustomAdapter;
import fall2018.csc2017.GameCentre.GestureDetectGridViews.GestureDetectGridViewShortPress;
import fall2018.csc2017.GameCentre.MovementControllers.MovementController;
import fall2018.csc2017.GameCentre.MovementControllers.MovementControllerSimplePress;
import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.SaveAndLoadBoardManager;

public class SimonGameActivity extends AppCompatActivity implements Observer {

    private SimonBoardManager simonBoardManager;
    private SimonMovementController movementControllerSimon;
    private ArrayList<Button> tileButtons;
    private Button replayButton;
    ListIterator<SimonTile> i;


    // Grid View and calculated column height and width based on device size
    private GestureDetectGridViewShortPress gridView;
    private static int columnWidth, columnHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        simonBoardManager = SaveAndLoadBoardManager.loadFromFile(this, SimonStartingActivity.SAVE_FILENAME);
        setContentView(R.layout.activity_simon);
//        addUndoButtonListener();
//        addSaveButtonListener();

        // Add View to activity
        gridView = findViewById(R.id.grid);
        replayButton = findViewById(R.id.replayButton);
        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayGameQueue();
                replayButton.setEnabled(false);
            }
        });
        createTileButtons(this);
        gridView.setNumColumns(simonBoardManager.getBoard().getDimension());
        movementControllerSimon = new SimonMovementController(simonBoardManager);
        gridView.setMovementController(movementControllerSimon);
        SimonTile t = simonBoardManager.randomizer();
        simonBoardManager.getGameQueue().add(t);
        simonBoardManager.getGameQueue().addObserver(this);
        movementControllerSimon.addObserver(this);
        // Observer sets up desired dimensions as well as calls our displayGameQueue function
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
                        //TODO see if maybe we can remove this.
                        displayGameQueue();
                    }
                });
        //TODO check if displayGameQueue() isn't called twice
    }

    private void createTileButtons(Context context){
        SimonTilesBoard board = simonBoardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != board.getDimension(); row++) {
            for (int col = 0; col != board.getDimension(); col++) {
               Button tmp = new Button(context);
                tmp.setBackground(ContextCompat.getDrawable(this, R.drawable.tile_blue));
                this.tileButtons.add(tmp);
            }
        }
    }

    private void createTileGUI(){
        if(i.hasPrevious()){
            int prevId = i.previous().getId();
            tileButtons.get(prevId).setBackground(ContextCompat.getDrawable(this, R.drawable.tile_blue));
            gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
            i.next();
        }
        //TODO Make sure this works
        if(i.hasNext()){
            final int currId = i.next().getId();
            tileButtons.get(currId).setBackground(ContextCompat.getDrawable(this, R.drawable.blank_tile));
            gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    createTileGUI();

                }
            }, 1500);
        }
        else{
            gridView.setMovementController(movementControllerSimon);
        }

    }
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof GameQueue) {
            displayGameQueue();

        }
        else if(o instanceof MovementController){
            tileButtons.get(movementControllerSimon.getCurrPosition()).setBackground(ContextCompat.getDrawable(this, R.drawable.lighterblue));
            gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tileButtons.get(movementControllerSimon.getCurrPosition()).setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tile_blue));

                    gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));


                }
            }, 200);
        }
    }

    private void displayGameQueue() {
        // create an empty movementController to be called when user clicks on tile
        // during display of the gameQueue
        MovementControllerSimplePress m = new MovementControllerSimplePress() {
            @Override
            public void processMove(Context context, int position) {
            }
        };
        gridView.setMovementController(m);
        i = simonBoardManager.getGameQueue().iterator();
        createTileGUI();

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
        SaveAndLoadBoardManager.saveToFile(this, SimonStartingActivity.SAVE_FILENAME, simonBoardManager);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(SimonGameActivity.this, SimonStartingActivity.class));
        finish();
    }

    private void addUndoButtonListener() {
        Button undoButton = findViewById(R.id.undoButton);
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undo();
            }
        });
    }

    //TODO: Need to implement what to do in case of undo
    private void undo(){

    }

    /**
     * Activate save button.
     */
    private void addSaveButtonListener() {
        Button saveButton = findViewById(R.id.SaveButton);
        final SimonGameActivity simonGameActivity = this;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveAndLoadBoardManager.saveToFile(simonGameActivity, SimonStartingActivity.SAVE_FILENAME, simonBoardManager);
                makeToastSavedText();
            }
        });
    }

    /**
     * Display that a game was saved successfully.
     */
    private void makeToastSavedText() {
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
    }
}
