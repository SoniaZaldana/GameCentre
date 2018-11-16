package fall2018.csc2017.GameCentre;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The SlidingTilesBoard Dimension + Undo Number Activity
 */
public class ChooseDimensionsActivity extends AppCompatActivity {
    EditText dimensionInput, undoInput;
    Button submitInput;
    TextView dimensionInstructions, undoInstructions;
    /**
     * the Uri of the image of the board, that the user requests with intent.
     */
    Uri tileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_complexity);
        dimensionInput = findViewById(R.id.EditDimension);
        submitInput = findViewById(R.id.submitEverything);
        dimensionInstructions = findViewById(R.id.dimensionInstructions);
        undoInput = findViewById(R.id.editUndo);
        undoInstructions = findViewById(R.id.undoInstructions);
        String tileImgAsStr = getIntent().getStringExtra("TileImage");
        if (tileImgAsStr != null) {
            tileImage = Uri.parse(tileImgAsStr);
        }
    }

    /**
     * Checks for valid inputs. Returns messages to request for valid inputs.
     *
     * @param view The view.
     */
    public void submitInput(View view) {

        String text = dimensionInput.getText().toString();
        String undoMaxText = undoInput.getText().toString();
        try {
            Integer dimension = Integer.parseInt(text);
            if (dimension > 9) {
                dimensionInstructions.setText("Please enter a valid number less than 10!");
            } else if (dimension == 1) {
                dimensionInstructions.setText("Too easy :) Try something harder!");

            } else {
                Intent tmp = new Intent(this, SlidingGameActivity.class);

                try {
                    Integer undoMax = Integer.parseInt(undoMaxText);

                    if (undoMax <= 0) {
                        undoInstructions.setText("Please enter a number greater than 0");
                    } else {
                        // Create the tiles
                        List<Tile> tilesList = new ArrayList<>();
                        final int numTiles = dimension * dimension;
                        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
                            tilesList.add(new Tile(tileNum + 1));
                        }
                        // Create the board
                        Iterator<Tile> iter = tilesList.iterator();
                        Tile[][] tiles = new Tile[dimension][dimension];
                        for (int row = 0; row != dimension; row++) {
                            for (int col = 0; col != dimension; col++) {
                                tiles[row][col] = iter.next();
                            }
                        }
                        SlidingTilesBoard slidingTilesBoard = new SlidingTilesBoard(dimension, tiles);

                        SlidingBoardManager slidingBoardManager = new SlidingBoardManager(undoMax, slidingTilesBoard);

                        if (tileImage != null) {
                            slidingBoardManager.getBoard().setPicturePath(tileImage.toString());
                        }
                        SaveAndLoadBoardManager.saveToFile(this, SlidingTilesStartingActivity.SAVE_FILENAME, slidingBoardManager);
                        startActivity(tmp);
                    }
                } catch (NumberFormatException e) {
                    undoInstructions.setText("Please enter a valid number!");
                }

            }
        } catch (NumberFormatException e) {
            dimensionInstructions.setText("Please enter a valid number!");
        }
    }
}
