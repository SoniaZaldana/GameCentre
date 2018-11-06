package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * The Board Dimension + Undo Number Activity
 */
public class ChooseDimensionsActivity extends AppCompatActivity {
    EditText dimensionInput, undoInput;
    Button submitInput;
    TextView dimensionInstructions, undoInstructions;
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
                Intent tmp = new Intent(this, GameActivity.class);

                try {
                    Integer undoMax = Integer.parseInt(undoMaxText);

                    if (undoMax <= 0) {
                        undoInstructions.setText("Please enter a number greater than 0");
                    } else {

                        BoardManager boardManager = new BoardManager(dimension, undoMax);
                        if (tileImage != null) {
                            boardManager.getBoard().setPicturePath(tileImage.toString());
                        }
                        SaveAndLoad.saveToFile(this, SlidingTilesStartingActivity.SAVE_FILENAME, boardManager);
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
