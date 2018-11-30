package fall2018.csc2017.GameCentre.MineSweeper;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import fall2018.csc2017.GameCentre.CustomAdapter;
import fall2018.csc2017.GameCentre.GestureDetectGridViews.GestureDetectGridViewLongPress;
import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.SaveAndLoadBoardManager;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.Toast;


//TODO Create a display for the flag counter, and get it from MovementControllerSweeper.
//TODO WIll probably have to implement observable.
public class MineSweeperActivity extends AppCompatActivity implements Observer {
    private MovementControllerSweeper movementControllerSweeper;
    private SweeperTilesBoard sweeperTilesBoard;
    private SweeperBoardManager sweeperBoardManager;
    private GestureDetectGridViewLongPress gridView;
    private ArrayList<Button> minesButtons;
    private static int columnWidth, columnHeight;
    private TextView timerText;
    private TextView healthNumber;
    private TextView bombTimerText;
    private Timer timer = new Timer();

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_sweeper_main);
        if (getIntent().hasExtra("Dimension")) {
            int dimension = getIntent().getExtras().getInt("Dimension");
            int complexity = getIntent().getExtras().getInt("Complexity");
            sweeperTilesBoard = new SweeperTilesBoard(dimension, complexity);
            sweeperBoardManager = new SweeperBoardManager(sweeperTilesBoard);
        } else {
            sweeperBoardManager = SaveAndLoadBoardManager.loadFromFile(this, SweeperStartingActivity.SWEEPER_SAVE_FILENAME);
            sweeperTilesBoard = sweeperBoardManager.getBoard();
        }
        int dimension = sweeperTilesBoard.getDimension();
        gridView = (GestureDetectGridViewLongPress) findViewById(R.id.grid);
        gridView.setNumColumns(dimension);
        startTimer();
        gridView = findViewById(R.id.grid);
        movementControllerSweeper = new MovementControllerSweeper(sweeperBoardManager);
        if (sweeperBoardManager.isBombActive()) {
            movementControllerSweeper.startTimer(sweeperBoardManager.getActiveBombRow(),
                    sweeperBoardManager.getActiveBombCol(), this);
        }
        gridView.setMovementController(movementControllerSweeper);
        createTileButtons(this);
        gridView.createAndSetGestureDetector(this);
        sweeperTilesBoard.addObserver(this);
        timerText = findViewById(R.id.timer);
        healthNumber = findViewById(R.id.HP);
        bombTimerText = findViewById(R.id.timeBomb);
        addSaveButtonListener();
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();
                        columnWidth = displayWidth / sweeperBoardManager.getBoard().getDimension();
                        columnHeight = displayHeight / sweeperBoardManager.getBoard().getDimension();
                        displayInitialView();
                    }
                });
    }

    /**
     * Creating the initial display of the board
     */
    public void displayInitialView() {
        createTileGUI();
        for (int i = 0; i < minesButtons.size(); i++) {
            SweeperTile tile = sweeperBoardManager.getTileInPosition(i);
            updateTileButtons(i, tile);
        }
        gridView.setAdapter(new CustomAdapter(minesButtons, columnWidth, columnHeight));
        updateTime();
        updateHealth();
        updateBombTime();
    }

    /**
     * Makes the background of all the tiles initially
     */
    public void createTileGUI() {
        for (Button mineButton : minesButtons) {
            mineButton.setBackground(ContextCompat.getDrawable(this, R.drawable.ms_tile));
        }
    }

    /**
     * Updates the time elapsed in the game visually
     */
    private void updateTime() {
        timerText.setText("Time: \n" + String.valueOf(sweeperBoardManager.getBoard().getTime()));
    }

    /**
     * Update the counter on the timer of the bomb
     */
    private void updateBombTime() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bombTimerText.setText("Bomb Time: " + String.valueOf(sweeperBoardManager.getBoard().getBombTime()));
            }
        });
    }

    /**
     * Updates the Health remaining
     */
    private void updateHealth() {
        healthNumber.setText("HP: " + String.valueOf(sweeperBoardManager.getBoard().getHitPoints()));
    }

    @Override
    protected void onPause() {
        super.onPause();
        movementControllerSweeper.getTimer().cancel();
        this.timer.cancel();
    }

    /**
     * Start the clock on the elapsed time of the game.
     */
    public void startTimer() {
        ScoreTask task = new ScoreTask(sweeperBoardManager);
        timer.schedule(task, 1000, 1000);
    }

    /**
     * Sets the background of the tile if it has a Time Bomb
     * @param buttonIndex index of the button
     */
    private void setTimerPicture(int buttonIndex) {
        minesButtons.get(buttonIndex).setBackground(ContextCompat.getDrawable(this,
                R.drawable.timebomb));
    }

    /**
     * Updates the display
     * @param location of the tile in an array [row, col]
     */
    public void display(int[] location) {
        if (location != null) {
            int row = location[0];
            int col = location[1];
            final int buttonIndex = (row * sweeperTilesBoard.getDimension()) + col;
            SweeperTile t = sweeperTilesBoard.getTile(row, col);
            BombTypes bombType = t.getBombType();
            if (t.isBombExploded()) {
                switch (bombType) {
                    case BIG:
                        endGame(buttonIndex);
                        break;
                    case SMALL:
                        minesButtons.get(buttonIndex).setBackground(ContextCompat.getDrawable(this,
                                R.drawable.smallbomb));
                        if (sweeperBoardManager.getBoard().getHitPoints() == 0) {
                            endGame(buttonIndex);
                        }
                        break;
                    case TIMED:
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setTimerPicture(buttonIndex);
                            }
                        });
                        if (sweeperBoardManager.getBoard().getBombTime() == 0) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    endGame(buttonIndex);
                                }
                            });
                        }
                        break;
                }
            } else {
                updateTileButtons(buttonIndex, t);
                gridView.setAdapter(new CustomAdapter(minesButtons, columnWidth, columnHeight));
            }
        }
        updateTime();
        updateHealth();
        updateBombTime();
    }

    /**
     * Creates the buttons to press for minesweeper
     * @param context This activity
     */
    private void createTileButtons(Context context) {
        SweeperTilesBoard slidingTilesBoard = sweeperBoardManager.getBoard();
        minesButtons = new ArrayList<>();
        for (int row = 0; row != slidingTilesBoard.getDimension(); row++) {
            for (int col = 0; col != slidingTilesBoard.getDimension(); col++) {
                Button tmp = new Button(context);
                this.minesButtons.add(tmp);
            }
        }
    }


    /**
     * Draws images on the tiles. Used when loading or creating a new game.
     *
     * @param buttonIndex The position of the tile
     * @param tile The tile to update
     */
    public void updateTileButtons(int buttonIndex, SweeperTile tile) {
        if (tile.isFlagged()) {
            minesButtons.get(buttonIndex).setBackground(
                    ContextCompat.getDrawable(this, R.drawable.flag));
        } else if (tile.getBombsAround() == 0) {
            minesButtons.get(buttonIndex).setBackground(
                    ContextCompat.getDrawable(this, R.drawable.empty_tile));
        } else if (tile.getBombsAround() != -1) {
            minesButtons.get(buttonIndex).setText(Integer.toString(tile.getBombsAround()));
            minesButtons.get(buttonIndex).setBackground(
                    ContextCompat.getDrawable(this, R.drawable.ms_tile));
            getSetTextSize(buttonIndex);
            setTextColor(buttonIndex, tile.getBombsAround());
        } else if (tile.isBombExploded()) {
            final int index = buttonIndex;
            BombTypes bombType = tile.getBombType();
            switch (bombType) {
                case BIG:
                    endGame(buttonIndex);
                    break;
                case SMALL:
                    minesButtons.get(buttonIndex).setBackground(ContextCompat.getDrawable(this,
                            R.drawable.smallbomb));
                    if (sweeperBoardManager.getBoard().getHitPoints() == 0) {
                        endGame(buttonIndex);
                    }
                    break;
                case TIMED:
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setTimerPicture(index);
                        }
                    });
                    if (sweeperBoardManager.getBoard().getBombTime() == 0) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                endGame(index);
                            }
                        });
                    }
                    break;
            }
        } else {
            minesButtons.get(buttonIndex).setBackground(ContextCompat.getDrawable(this, R.drawable.ms_tile));
        }
    }

    /**
     * Change the text size depending on the size of the board
     *
     * @param buttonIndex The location of the tile
     */
    private void getSetTextSize(int buttonIndex) {
        if (sweeperTilesBoard.getDimension() == 8) {
            minesButtons.get(buttonIndex).setTextSize(20);
        } else if (sweeperTilesBoard.getDimension() == 16) {
            minesButtons.get(buttonIndex).setTextSize(10);
        } else {
            minesButtons.get(buttonIndex).setTextSize(6);
        }
    }

    /**
     * Changes the color of the text for button located in buttonIndex based on the
     * numberOfBombsAround parameter
     *
     * @param buttonIndex      the location of button in minesButtons
     * @param numOfBombsAround the number to be displayed as a text
     */
    private void setTextColor(int buttonIndex, int numOfBombsAround) {
        Button currentButton = minesButtons.get(buttonIndex);
        if (numOfBombsAround == 1) {
            currentButton.setTextColor(Color.RED);
        } else if (numOfBombsAround == 2) {
            currentButton.setTextColor(Color.GREEN);
        } else if (numOfBombsAround == 3) {
            currentButton.setTextColor(Color.BLUE);
        } else if (numOfBombsAround == 4) {
            currentButton.setTextColor(Color.YELLOW);
        } else if (numOfBombsAround == 5) {
            currentButton.setTextColor(Color.GRAY);
        }
    }
    /**
     * Activate save button.
     */
    private void addSaveButtonListener() {
        Button saveButton = findViewById(R.id.SaveButton);
        final MineSweeperActivity sweeperGameActivity = this;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveAndLoadBoardManager.saveToFile(sweeperGameActivity, SweeperStartingActivity.SWEEPER_SAVE_FILENAME, sweeperBoardManager);
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
     * Reveals all the bombs on the board, by displaying all of them.
     *
     * @param buttonIndex the button which by clicking the game was ended
     */
    public void endGame(int buttonIndex) {
        minesButtons.get(buttonIndex).setBackground(ContextCompat.getDrawable(this,
                R.drawable.exploded_bomb));
        gridView.setAdapter(new CustomAdapter(minesButtons, columnWidth, columnHeight));
        int i = 0;
        for (SweeperTile mine : sweeperTilesBoard) {
            if (i != buttonIndex && mine.hasBomb()) {
                if (mine.getBombType().equals(BombTypes.BIG)) {
                    minesButtons.get(i).setBackground(ContextCompat.getDrawable(this,
                            R.drawable.normal_bomb));
                } else if (mine.getBombType().equals(BombTypes.SMALL)) {
                    minesButtons.get(i).setBackground(ContextCompat.getDrawable(this,
                            R.drawable.smallbomb));
                } else if (mine.getBombType().equals(BombTypes.TIMED)) {
                    minesButtons.get(i).setBackground(ContextCompat.getDrawable(this,
                            R.drawable.timebomb));
                }
            }
            i++;
        }
        Toast.makeText(this, "You lost", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void update(Observable observable, Object o) {
        int[] myArray = (int[]) o;
        display(myArray);
    }

    /**
     * A timer task that increments the timer.
     */
    private class ScoreTask extends TimerTask {

        /**
         * The boardmanager that uses this timer.
         */
        private SweeperBoardManager manager;

        /**
         * An task that increments the time.
         *
         * @param manager The board manager this is acting on
         */
        public ScoreTask(SweeperBoardManager manager) {
            super();
            this.manager = manager;
        }

        /**
         * The task this timer does.
         */
        public void run() {
            this.manager.getBoard().timeIncrement();
        }

    }
}


