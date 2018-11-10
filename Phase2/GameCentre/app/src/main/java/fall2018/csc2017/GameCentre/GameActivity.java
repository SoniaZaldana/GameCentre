package fall2018.csc2017.GameCentre;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;


/**
 * The game activity.
 */
public class GameActivity extends AppCompatActivity implements Observer {
    /**
     * Constant which represents the read storage request code.
     * Use it to check if getting return for a READ_STORAGE request.
     */
    static final int MY_PERMISSIONS_REQUEST_READ_STORAGE = 3;

    /**
     * The board manager.
     */
    private BoardManager boardManager;

    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;

    // Grid View and calculated column height and width based on device size
    private GestureDetectGridView gridView;
    private static int columnWidth, columnHeight;
    /**
     * Timer used for autosave
     */
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
        super.onCreate(savedInstanceState);
        boardManager = SaveAndLoadBoardManager.loadFromFile(this, SlidingTilesStartingActivity.SAVE_FILENAME);
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
        SlidingTilesBoard slidingTilesBoard = boardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != slidingTilesBoard.getNumRows(); row++) {
            for (int col = 0; col != slidingTilesBoard.getNumCols(); col++) {
                Button tmp = new Button(context);
                this.tileButtons.add(tmp);
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
                SaveAndLoadBoardManager.saveToFile(gameActivity, SlidingTilesStartingActivity.SAVE_FILENAME, boardManager);
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
        SlidingTilesBoard slidingTilesBoard = boardManager.getBoard();
        // See if you need the permission. Code taken from Developer Website for Android.
        if (slidingTilesBoard.getPicturePath() != null) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    // requires permission rationale.
                } else {
                    // No explanation needed; request the permission
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_READ_STORAGE);
                }

            } else { // Don't need the permission, but request it either way, as previous activity may have been destroyed.
                // Otherwise, might give a security permission error.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_STORAGE);
            }
        } else {
            createTileGUI(ContextCompat.getDrawable(this, R.drawable.tile_16));
        }

    }

    /**
     * Creates how the tiles looks: number on it, image, etc.
     * @param d drawable to set as background for a tile
     */
    private void createTileGUI(Drawable d) {
        SlidingTilesBoard slidingTilesBoard = boardManager.getBoard();
        int nextPos = 0;
        int numberOnTile;
        for (Button b : tileButtons) {
            int row = nextPos / slidingTilesBoard.getNumRows();
            int col = nextPos % slidingTilesBoard.getNumCols();
            numberOnTile = slidingTilesBoard.getTile(row, col).getId();
            if (numberOnTile != slidingTilesBoard.getBlankId()) {
                b.setText(String.valueOf(numberOnTile));
                b.setBackground(d);

            } else {
                b.setText("");
                b.setBackground(ContextCompat.getDrawable(this, R.drawable.tile_16));
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
        SaveAndLoadBoardManager.saveToFile(this, SlidingTilesStartingActivity.SAVE_FILENAME, boardManager);
        timer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    /**
     * Auto-saves the game every 5 seconds
     */
    public void autoSave() {
        SaveTask task = new SaveTask(this);
        timer.schedule(task, 5000, 5000);
    }

    /**
     * Undoes the previous step.
     */
    public void undo() {
        boolean undone = this.boardManager.undo();
        if (!undone) {
            makeUndoFailedText();
        }
    }

    /**
     * The toast created when undo fails.
     */
    private void makeUndoFailedText() {
        Toast.makeText(this, "Maximum Undo reached", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay!
                    getTileImageAndMakeGUI();
                } else {
                    // permission denied/
                    createTileGUI(ContextCompat.getDrawable(this, R.drawable.tile_16));


                }
                return;
            }

        }
    }

    /**
     * Retrieves the tile image from memory based on URI path, and creates the tile GUI.
     */
    private void getTileImageAndMakeGUI() {
        try {
            InputStream stream = getContentResolver().openInputStream(Uri.parse(boardManager.getBoard().getPicturePath()));
            Drawable d = Drawable.createFromStream(stream, "UserTilePic");
            createTileGUI(d);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "Image not found", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        display();
    }

    /**
     * A task for the timer to do.
     */
    public class SaveTask extends TimerTask {
        /**
         * The current GameActivity instance.
         */
        private GameActivity gameActivity;

        /**
         * An auto save timer
         *
         * @param gameActivity The gameActivity that uses this timer
         */
        SaveTask(GameActivity gameActivity) {
            super();
            this.gameActivity = gameActivity;
        }

        /**
         * The command being ran by the timer.
         */
        public void run() {
            SaveAndLoadBoardManager.saveToFile(this.gameActivity, SlidingTilesStartingActivity.SAVE_FILENAME, boardManager);

        }
    }
}
