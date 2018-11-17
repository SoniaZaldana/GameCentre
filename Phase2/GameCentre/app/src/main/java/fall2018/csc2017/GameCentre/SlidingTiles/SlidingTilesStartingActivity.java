package fall2018.csc2017.GameCentre.SlidingTiles;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import fall2018.csc2017.GameCentre.ChooseDimensionsActivity;
import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.SaveAndLoadBoardManager;

/**
 * The initial activity for the sliding puzzle tile game.
 * Code for picking image inspired from http://androidbitmaps.blogspot.com/2015/04/loading-images-in-android-part-iii-pick.html
 */
public class SlidingTilesStartingActivity extends AppCompatActivity {

    /**
     * The main save file.
     */
    public static String SAVE_FILENAME;

    /**
     * The board manager.
     */
    private SlidingBoardManager slidingBoardManager;
    /**
     * Path to the tile Picture image.
     */
    private String tilePicture;

    private Context context;
    /**
     * Request code to send and get when sending intent to pick image.
     * Use to check if receiving the data from the right intent.
     */
    private static final int PICK_IMAGE_REQ_CODE = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_starting_);
        addStartButtonListener();
        addLoadButtonListener();
        addPictureButtonListener();

        SharedPreferences currentUsername = getApplicationContext().getSharedPreferences("sharedUser", MODE_PRIVATE);
        String user = currentUsername.getString("thisUser", "User");
        SAVE_FILENAME = user + "save_file.ser";

    }

    /**
     * Activate the start button.
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });
    }

    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.LoadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingBoardManager = SaveAndLoadBoardManager.loadFromFile(context, SAVE_FILENAME);
                if (slidingBoardManager == null) {
                    Toast.makeText(getApplicationContext(), "No previously saved game.", Toast.LENGTH_SHORT).show();
                } else {
                    makeToastLoadedText();
                    loadGame();
                }

            }
        });
    }

    /**
     * Activate the choose picture
     */
    private void addPictureButtonListener() {
        Button choosePicture = findViewById(R.id.ChooseTilePicture);
        choosePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_OPEN_DOCUMENT, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);

                startActivityForResult(gallery, PICK_IMAGE_REQ_CODE);
            }
        });
    }

    /**
     * Get the picture, and set it to the board.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // If the result is our code, that means we are getting the result from the gallery activity
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQ_CODE) {
            // data returns the Uri(address) of the selected picture
            tilePicture = data.getData().toString();
            slidingBoardManager = SaveAndLoadBoardManager.loadFromFile(context, SAVE_FILENAME);
            if (slidingBoardManager != null) {
                SlidingTilesBoard slidingTilesBoard = slidingBoardManager.getBoard();
                slidingTilesBoard.setPicturePath(tilePicture);
                SaveAndLoadBoardManager.saveToFile(context, SAVE_FILENAME, slidingBoardManager);
            }
        }
    }


    /**
     * Display that a game was loaded successfully.
     */
    private void makeToastLoadedText() {
        Toast.makeText(this, "Loaded Game", Toast.LENGTH_SHORT).show();
    }


    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
        slidingBoardManager = SaveAndLoadBoardManager.loadFromFile(this, SAVE_FILENAME);
    }

    /**
     * Switch to the SlidingGameActivity view to play the game.
     */
    private void loadGame() {
        Intent tmp = new Intent(this, SlidingGameActivity.class);
        startActivity(tmp);


    }

    private void newGame() {
        Intent tmp = new Intent(this, ChooseDimensionsActivity.class);
        if (tilePicture != null) {
            tmp.putExtra("TileImage", tilePicture);
        }

        startActivity(tmp);


    }
}
