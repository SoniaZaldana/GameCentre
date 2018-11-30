/**
 * Excluded from tests because it is a view class.
 */
package fall2018.csc2017.GameCentre;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

import fall2018.csc2017.GameCentre.Simon.SimonBoardManager;
import fall2018.csc2017.GameCentre.Simon.SimonGameActivity;
import fall2018.csc2017.GameCentre.Simon.SimonStartingActivity;
import fall2018.csc2017.GameCentre.SlidingTiles.GameInputValidator;
import fall2018.csc2017.GameCentre.SlidingTiles.SlidingBoardManager;
import fall2018.csc2017.GameCentre.SlidingTiles.SlidingGameActivity;
import fall2018.csc2017.GameCentre.SlidingTiles.SlidingTilesStartingActivity;

public class ChooseDimensionActivity extends AppCompatActivity{

    private EditText dimensionInput, undoInput;
    private String game;
    private Uri tileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_dimension);
        dimensionInput = findViewById(R.id.EditDimension);
        undoInput = findViewById(R.id.editUndo);

        game = getIntent().getStringExtra("Game");
        if (game.equals(null)) {
            Bundle extras = getIntent().getExtras();
            game = extras.getString("Game");
            String tileImgAsStr = getIntent().getStringExtra("TileImage");
            if (tileImgAsStr != null) {
                tileImage = Uri.parse(tileImgAsStr);
            }
        }
    }

    /**
     * Submits the input (dimension and maximum number of undo) for either simon or SlidingTIles. It also validates
     * input is valid using the class GameInputValidator
     * @param view
     */
    public void submitInput(View view) {
        String dimensionText = dimensionInput.getText().toString();
        String undoMaxText = undoInput.getText().toString();
        Map<Boolean, String> processedResult = GameInputValidator.processInput(dimensionText, undoMaxText);
        if (processedResult.containsKey(true)) {
            Integer dimension = Integer.parseInt(dimensionText);
            Integer undo = Integer.parseInt(undoMaxText);

            if (game.equals("SlidingTiles")) {
                SlidingBoardManager slidingBoardManager = new SlidingBoardManager(dimension, undo);
                if (tileImage != null) {
                    slidingBoardManager.getBoard().setPicturePath(tileImage.toString());
                }
                SaveAndLoadBoardManager.saveToFile(this, SlidingTilesStartingActivity.SAVE_FILENAME, slidingBoardManager);
                startActivity(new Intent(this, SlidingGameActivity.class));
            }

            else if (game.equals("simon")) {
                final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.startgame);
                mp.start();
                SimonBoardManager simonBoardManager = new SimonBoardManager(dimension, undo);
                SaveAndLoadBoardManager.saveToFile(this, SimonStartingActivity.SAVE_FILENAME, simonBoardManager);
                startActivity(new Intent(this, SimonGameActivity.class));
            }
        }
        else{
            Toast.makeText(this, processedResult.get(false), Toast.LENGTH_SHORT).show();
        }
    }
}

