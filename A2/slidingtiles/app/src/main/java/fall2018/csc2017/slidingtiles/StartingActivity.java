package fall2018.csc2017.slidingtiles;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The initial activity for the sliding puzzle tile game.
 */
public class StartingActivity extends AppCompatActivity {

    /**
     * The main save file.
     */
    public static String SAVE_FILENAME = "save_file.ser";

    /**
     * The board manager.
     */
    private BoardManager boardManager;

    private Context context;
    //TODO Get rid of all the extra Save and Loads here. Only use when necessary.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //boardManager = new BoardManager();
        //saveToFile(SAVE_FILENAME);
        context = this;
        setContentView(R.layout.activity_starting_);
        addStartButtonListener();
        addLoadButtonListener();

        SharedPreferences currentUsername = getApplicationContext().getSharedPreferences("sharedUser", MODE_PRIVATE);
        String user = currentUsername.getString("thisUser", "User");
        SAVE_FILENAME = user + SAVE_FILENAME;

    }

    /**
     * Activate the start button.
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //make default 3
                //boardManager = new BoardManager(3);
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
                boardManager = SaveAndLoad.loadFromFile(context, SAVE_FILENAME);
                //SaveAndLoad.saveToFile(context,SAVE_FILENAME , boardManager);
                makeToastLoadedText();
                loadGame();
            }
        });
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
        boardManager = SaveAndLoad.loadFromFile(this, SAVE_FILENAME);
    }

    /**
     * Switch to the GameActivity view to play the game.
     */
    private void loadGame() {
        Intent tmp = new Intent(this, GameActivity.class);
        //SaveAndLoad.saveToFile(this, StartingActivity.SAVE_FILENAME, boardManager);
        startActivity(tmp);


    }
    private void newGame(){
        //TODO make sure that even if i have a loaded game. If I choose to play a new game, it plays a new game.
        //TODO Figure out difference between temp_save and save_file
        Intent tmp = new Intent(this, ChooseDimensionsActivity.class);
        //SaveAndLoad.saveToFile(this, StartingActivity.TEMP_SAVE_FILENAME, boardManager);
        startActivity(tmp);


    }
}
