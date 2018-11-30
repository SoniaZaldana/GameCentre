package fall2018.csc2017.GameCentre.Simon;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import fall2018.csc2017.GameCentre.Board;
import fall2018.csc2017.GameCentre.CustomAdapter;
import fall2018.csc2017.GameCentre.GestureDetectGridViews.GestureDetectGridViewShortPress;
import fall2018.csc2017.GameCentre.MovementControllers.MovementModel;
import fall2018.csc2017.GameCentre.MovementControllers.MovementModelSimplePress;
import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.SaveAndLoadBoardManager;

public class SimonGameActivity extends AppCompatActivity implements Observer {

    private SimonBoardManager simonBoardManager;
    private MovementModelSimon movementControllerSimon;
    private ArrayList<Button> tileButtons;
    private ListIterator<SimonTile> i;
    private Button saveButton;
    private Button undoButton;


    // Grid View and calculated column height and width based on device size
    private GestureDetectGridViewShortPress gridView;
    private static int columnWidth, columnHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        simonBoardManager = SaveAndLoadBoardManager.loadFromFile(this, SimonStartingActivity.SAVE_FILENAME);
        setContentView(R.layout.simon_main);
        addUndoButtonListener();
        addSaveButtonListener();

        // Add View to activity
        gridView = findViewById(R.id.grid);
        assignRandomColourToTile();

        createTileButtons(this);
        gridView.setNumColumns(simonBoardManager.getBoard().getDimension());
        movementControllerSimon = new MovementModelSimon(simonBoardManager);
        gridView.setMovementController(movementControllerSimon);
        SimonTile t = simonBoardManager.randomizer();
        // only add 1 element to queue if it is a new queue
        if(simonBoardManager.getGameQueue().getSize()==0){
            simonBoardManager.getGameQueue().add(t);
        }
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
                        displayGameQueue();
                    }
                });
    }

    /**
     * Assigns a random colour to a tile from a given list of colors
     */
    private void assignRandomColourToTile() {
        // get list o colors for the simon tiles
        TypedArray ta = getResources().obtainTypedArray(R.array.colors);
        int size = ta.length();
        //get a random color from resources and assign it to a tile
        Random random = new Random();
        for(ArrayList<SimonTile> arr: simonBoardManager.getBoard().getAllTiles()){
            for(SimonTile t: arr){
                t.setColor(ta.getResourceId(random.nextInt(size), 0));
            }
        }
    }

    /**
     * Takes the tiles in the board and makes them blue buttons
     * @param context
     */
    private void createTileButtons(Context context){
        Board board = simonBoardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != board.getDimension(); row++) {
            for (int col = 0; col != board.getDimension(); col++) {
               Button tmp = new Button(context);
                tmp.setBackground(ContextCompat.getDrawable(this, R.drawable.tile_blue));
                this.tileButtons.add(tmp);
            }
        }
    }

    /**
     * Displays the tile graphical interface i.e. changing colours as each tile is displayed
     */
    private void createTileGUI(){
        // set the colour of the previous button in gamequeue back to blue
        // as we've already displayed it
        if(i.hasPrevious()){
            int prevId = i.previous().getId();
            tileButtons.get(prevId).setBackground(ContextCompat.getDrawable(this, R.drawable.tile_blue));
            gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
            i.next();
        }
        // highlight current item in the gamequeue
        if(i.hasNext()){
            final int currId = i.next().getId();
            int tileColor = getResources().getColor(simonBoardManager.getTileInPosition(currId).getColor(), null);
            tileButtons.get(currId).setBackgroundColor(tileColor);
            gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    createTileGUI();

                }
            }, 1500 - (movementControllerSimon.getRound()*20));
        }
        else{
            gridView.setMovementController(movementControllerSimon);
        }

    }
    @Override
    public void update(Observable o, Object arg) {
        // if gamequeue updated, then display the stack.
        if (o instanceof GameQueue) {
            displayGameQueue();

        }
        // if a new button pressed, add appropriate resources(highlight, sound)
        else if(o instanceof MovementModel){
            int currId = movementControllerSimon.getCurrPosition();
            int currTilecolor = simonBoardManager.getTileInPosition(currId).getColor();
            int tileColor =getResources().getColor(currTilecolor,null);
            // flash the color of the tile
            tileButtons.get(currId).setBackgroundColor(tileColor);
            // make a sound
            final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.tap);
            mp.start();
            // update display
            gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
            // set the button back to original colour after 200ms
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

    /**
     * Displays the pattern of random tiles in the game queue
     */
    private void displayGameQueue() {
        // create an empty movementController to be called when user clicks on tile
        // during display of the gameQueue
        MovementModelSimplePress m = new MovementModelSimplePress() {
            @Override
            public void processMove(Context context, int position) {
            }
        };
        gridView.setMovementController(m);
        // restart the iterator, as we are displaying the queue from the start
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

    /**
     * Activate undo button
     */
    private void addUndoButtonListener() {
        undoButton = findViewById(R.id.undoButton);
        undoButton.setText("Redo Patterns Left: " + simonBoardManager.getUndo());
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undo();
            }
        });
    }

    /**
     * Decreases the number of undos left and redisplays the pattern presented to the user
     * as a second chance to get the pattern right
     */
    private void undo(){
        if (!(simonBoardManager.getUndo() == 0)){
            simonBoardManager.reduceUndo();
            Toast.makeText(this, "One redo used!", Toast.LENGTH_LONG).show();
            undoButton.setText("Redo Patterns Left: " + simonBoardManager.getUndo());
            displayGameQueue();
            if (simonBoardManager.getUndo() == 0){
                undoButton.setEnabled(false);
            }
        }
    }

    /**
     * Activate save button.
     */
    private void addSaveButtonListener() {
        saveButton = findViewById(R.id.saveButton);
        final SimonGameActivity simonGameActivity = this;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.saved);
                mp.start();
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
