package fall2018.csc2017.GameCentre.Simon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.SaveAndLoadBoardManager;

public class ChooseDimensionSimonActivity extends AppCompatActivity {

    private EditText dimensionInput, undoInput;
    private Button submitInput;
    private TextView dimensionInstructions, undoInstructions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_dimension_simon);

        dimensionInput = findViewById(R.id.EditDimension);
        submitInput = findViewById(R.id.submitEverything);
        dimensionInstructions = findViewById(R.id.dimensionInstructions);
        undoInput = findViewById(R.id.editUndo);
        undoInstructions = findViewById(R.id.undoInstructions);

    }

    public void submitInput(View view){
        String dimensionText = dimensionInput.getText().toString();
        String undoText = undoInput.getText().toString();
        try {
            Integer dimension = Integer.parseInt(dimensionText);
            if (dimension > 5) {
                dimensionInstructions.setText("Please enter a valid number less than 5!");
            } else if (dimension == 1) {
                dimensionInstructions.setText("Too easy :) Try something harder!");

            } else {
                try {
                    Integer undoMax = Integer.parseInt(undoText);
                    if (undoMax <= 0) {
                        undoInstructions.setText("Please enter a number greater than 0");
                    } else {
                        // Create the tiles
                        List<SimonTile> tilesList = new ArrayList<>();
                        final int numTiles = dimension * dimension;
                        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
                            tilesList.add(new SimonTile(tileNum + 1));
                        }
                        SimonTilesBoard simonTilesBoard = new SimonTilesBoard(dimension, tilesList);
                        SimonBoardManager simonBoardManager = new SimonBoardManager(simonTilesBoard, undoMax);
                        SaveAndLoadBoardManager.saveToFile(this, SimonStartingActivity.SIMON_SAVE_FILENAME, simonBoardManager);
                        startActivity(new Intent(this, SimonGameActivity.class));
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
