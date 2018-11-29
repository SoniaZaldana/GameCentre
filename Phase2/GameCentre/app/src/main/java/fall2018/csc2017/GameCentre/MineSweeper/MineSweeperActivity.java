package fall2018.csc2017.GameCentre.MineSweeper;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

//TODO Create a display for the flag counter, and get it from MovementControllerSweeper.
//TODO WIll probably have to implement observable.
public class MineSweeperActivity extends AppCompatActivity implements Observer {
    private MovementControllerSweeper movementControllerSweeper;
    private SweeperTilesBoard sweeperTilesBoard;
    private SweeperBoardManager sweeperBoardManager;
    private GestureDetectGridViewLongPress gridView;
    private ArrayList<SweeperTile> minesTiles;
    private ArrayList<Button> minesButtons;
    int dimension, complexity;
    private static int columnWidth, columnHeight;
    private TextView timerText;
    private TextView healthNumber;
    private TextView bombTimerText;
    private Timer timer = new Timer();

    @Override
    public void onCreate(Bundle bundle) {

        super.onCreate(bundle);
        setContentView(R.layout.activity_sweeper);
        String dimensionIndicator = getIntent().getExtras().getString("Dimension");
        String complexityIndicator = getIntent().getExtras().getString("Complexity");
        dimension = getDimension(dimensionIndicator);
        complexity = getComplexity(complexityIndicator);
        gridView = (GestureDetectGridViewLongPress) findViewById(R.id.grid);
        gridView.setNumColumns(dimension);
        minesTiles = getMinesTiles();
        sweeperTilesBoard = new SweeperTilesBoard(dimension, minesTiles);
        sweeperBoardManager = new SweeperBoardManager(sweeperTilesBoard);
        startTimer();
        gridView = findViewById(R.id.grid);
        gridView.setNumColumns(sweeperBoardManager.getBoard().getDimension());
        movementControllerSweeper = new MovementControllerSweeper(sweeperBoardManager);
        gridView.setMovementController(movementControllerSweeper);
        createTileButtons(this);
        gridView.createAndSetGestureDetector(this);
        sweeperTilesBoard.addObserver(this);
        timerText = findViewById(R.id.timer);
        healthNumber = findViewById(R.id.HP);
        bombTimerText = findViewById(R.id.timeBomb);
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
    public void displayInitialView() {
        createTileGUI();
        gridView.setAdapter(new CustomAdapter(minesButtons, columnWidth, columnHeight));
        updateTime();
        updateHealth();
        updateBombTime();
    }

    private void updateTime(){
        timerText.setText(String.valueOf(sweeperBoardManager.getBoard().getTime()));
    }

    private void updateBombTime(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bombTimerText.setText(String.valueOf(sweeperBoardManager.getBoard().getBombTime()));
            }
        });
    }

    private void updateHealth(){
        healthNumber.setText(String.valueOf(sweeperBoardManager.getBoard().getHitPoints()));
    }

    @Override
    protected void onPause(){
        super.onPause();
        sweeperBoardManager.stopTimer();
        movementControllerSweeper.getTimer().cancel();
        this.timer.cancel();
    }

    public void startTimer(){
        ScoreTask task = new ScoreTask(sweeperBoardManager);
        timer.schedule(task, 1000, 1000);
    }

    /**
     * A timer task that increments the timer.
     */
    private class ScoreTask extends TimerTask {

        /**
         *
         * The boardmanager that uses this timer.
         */
        private SweeperBoardManager manager;

        /**
         * An task that increments the time.
         *
         * @param manager The board manager this is acting on
         */
        public ScoreTask(SweeperBoardManager manager){
            super();
            this.manager = manager;
        }

        /**
         * The task this timer does.
         */
        public void run(){
            this.manager.getBoard().timeIncrement();
        }

    }

    private void setTimerPicture(int buttonIndex){
        minesButtons.get(buttonIndex).setBackground(ContextCompat.getDrawable(this,
                R.drawable.timebomb));
    }

    public void display(int[] location) {
        if (location != null) {
            int row = location[0];
            int col = location[1];
            final int buttonIndex = (row * dimension) + col;
            SweeperTile t = sweeperTilesBoard.getTile(row, col);
            if (t.isBombExploded()) {
                if (t.getBombType().equals("big")){
                    endGame(buttonIndex);
                } else if (t.getBombType().equals("small")){
                    minesButtons.get(buttonIndex).setBackground(ContextCompat.getDrawable(this,
                            R.drawable.smallbomb));
                    if (sweeperBoardManager.getBoard().getHitPoints() == 0){
                        endGame(buttonIndex);
                    }
                } else if (t.getBombType().equals("timed")){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setTimerPicture(buttonIndex);
                            }
                        });
                    if (sweeperBoardManager.getBoard().getBombTime() == 0){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                endGame(buttonIndex);
                            }
                        });
                    }
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

    private int getComplexity(String complexityIndicator) {
        if (complexityIndicator.equals("Easy")) {
            return 15;
        } else if (complexityIndicator.equals("Normal")) {
            return 20;
        } else {
            return 30;
        }
    }

    private int getDimension(String dimensionIndicator) {
        if (dimensionIndicator.equals("Small")) {
            return 8;
        } else if (dimensionIndicator.equals("Medium")) {
            return 13;
        } else {
            return 18;
        }
    }
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

   private ArrayList<SweeperTile> getMinesTiles() {
        ArrayList<SweeperTile> tilesList = new ArrayList<>();
        int numOfMines = dimension * dimension * complexity / 100;
        ArrayList locationOfTilesWithMine = randomlyChoose(numOfMines);
       for (int i = 0; i < dimension * dimension; i++) {
           if (locationOfTilesWithMine.contains(i)) {
                tilesList.add(new SweeperTile(true));
           } else {
               tilesList.add(new SweeperTile(false));
           }
       }
       return tilesList;
    }

    private ArrayList randomlyChoose(int numOfMines) {
        int i = 0;
        Random myRandom = new Random();
        ArrayList locationOfTilesWithMine = new ArrayList<>();
        while (i != numOfMines) {
            int location = myRandom.nextInt(dimension * dimension);
            if (!locationOfTilesWithMine.contains(location)) {
                locationOfTilesWithMine.add(location);
                i++;
            }
        }
        return locationOfTilesWithMine;

    }

    public void createTileGUI() {
        for (Button mineButton : minesButtons) {
            mineButton.setBackground(ContextCompat.getDrawable(this, R.drawable.ms_tile));
        }
    }


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
        } else {
            minesButtons.get(buttonIndex).setBackground(ContextCompat.getDrawable(this, R.drawable.ms_tile));
        }
    }

    private void getSetTextSize(int buttonIndex) {
        if (dimension == 8) {
            minesButtons.get(buttonIndex).setTextSize(20);
        } else if (dimension == 16) {
            minesButtons.get(buttonIndex).setTextSize(10);
        } else {
            minesButtons.get(buttonIndex).setTextSize(6);
        }
    }

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


    public void endGame(int buttonIndex) {
        minesButtons.get(buttonIndex).setBackground(ContextCompat.getDrawable(this,
                R.drawable.exploded_bomb));
        gridView.setAdapter(new CustomAdapter(minesButtons, columnWidth, columnHeight));
        int i = 0;
        for (SweeperTile mine: sweeperTilesBoard) {
            if (i != buttonIndex && mine.hasBomb()) {
                if (mine.getBombType().equals("big")) {
                    minesButtons.get(i).setBackground(ContextCompat.getDrawable(this,
                            R.drawable.normal_bomb));
                } else if (mine.getBombType().equals("small")){
                    minesButtons.get(i).setBackground(ContextCompat.getDrawable(this,
                            R.drawable.smallbomb));
                } else if (mine.getBombType().equals("timed")){
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
}


