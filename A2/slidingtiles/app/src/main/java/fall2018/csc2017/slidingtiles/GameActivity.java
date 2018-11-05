package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Stack;

import static android.widget.TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM;

/**
 * The game activity.
 */
public class GameActivity extends AppCompatActivity implements Observer {

    /**
     * The board manager.
     */
    private BoardManager boardManager;

    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;

    /**
     * Constants for swiping directions. Should be an enum, probably.
     */
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    // Grid View and calculated column height and width based on device size
    private GestureDetectGridView gridView;
    private static int columnWidth, columnHeight;

    // AutoSave Timer
    private Timer timer = new Timer();

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    // Display
    public void display() {
        updateTileButtons();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO Make sure everything is in the right order. Only if new game, then you take value from dimensions
        //TODO Make sure we don't switch up the values.
        super.onCreate(savedInstanceState);
        boardManager = SaveAndLoad.loadFromFile(this, StartingActivity.SAVE_FILENAME);
        setContentView(R.layout.activity_main);
        autoSave();

        addUndoButtonListener();
        addSaveButtonListener();

        // Add View to activity
        gridView = findViewById(R.id.grid);
        createTileButtons(this);
        gridView.setNumColumns(boardManager.getBoard().getNumCols());
        gridView.setBoardManager(boardManager);
        boardManager.getBoard().addObserver(this);
        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / boardManager.getBoard().getNumCols();
                        columnHeight = displayHeight / boardManager.getBoard().getNumRows();

                        display();
                    }
                });
    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    private void createTileButtons(Context context) {
        Board board = boardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != board.getNumRows(); row++) {
            for (int col = 0; col != board.getNumCols(); col++) {
                Button tmp = new Button(context);
               // tmp.setBackgroundResource(board.getTile(row, col).getBackground());
                // tmp.setBackgroundResource(R.drawable.tile_16);
                this.tileButtons.add(tmp);
                /*TextView tvId = (TextView) findViewById(R.id.number);
                tvId.setText("1");*/
            }
        }
    }

    /**
     * Activate the undo button.
     */
    private void addUndoButtonListener() {
        Button undoButton = findViewById(R.id.UndoButton);
        undoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        undo();
                    }
        });
    }

    /**
     * Activate save button.
     */
    private void addSaveButtonListener() {
        Button saveButton = findViewById(R.id.SaveButton);
        final GameActivity gameActivity = this;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveAndLoad.saveToFile(gameActivity, StartingActivity.SAVE_FILENAME, boardManager);
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

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    private void updateTileButtons() {
        Board board = boardManager.getBoard();
        int nextPos = 0;
        int numberOnTile;
        for (Button b : tileButtons) {
            int row = nextPos / board.getNumRows();
            int col = nextPos % board.getNumCols();
            b.setBackgroundResource(R.drawable.tile_16);
            numberOnTile = board.getTile(row, col).getId();
            if(numberOnTile != board.getBlankId()){
                b.setText(String.valueOf(numberOnTile));
            }
            else{
                b.setText("");
            }
            nextPos++;
        }

    }
    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        SaveAndLoad.saveToFile(this, StartingActivity.SAVE_FILENAME, boardManager);
        timer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    /**
     * Auto-saves the game every 5 seconds
     *
     */
    public void autoSave(){
        SaveTask task = new SaveTask(this);
        timer.schedule(task, 5000, 5000);
    }

    /**
     * Undo the board manager.
     */
    public void undo() {
        this.boardManager.undo();
        }


    @Override
    public void update(Observable o, Object arg) {
        display();
    }

    /**
     * A task for the timer to do.
     */
    public class SaveTask extends TimerTask {
        private GameActivity gameActivity;

        SaveTask(GameActivity gameActivity){
            super();
            this.gameActivity = gameActivity;
        }

        public void run(){
            SaveAndLoad.saveToFile(this.gameActivity, StartingActivity.SAVE_FILENAME, boardManager);

        }
    }
}
