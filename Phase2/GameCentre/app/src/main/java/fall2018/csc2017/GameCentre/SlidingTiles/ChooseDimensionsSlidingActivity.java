package fall2018.csc2017.GameCentre.SlidingTiles;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.SaveAndLoadBoardManager;

/**
 * The SlidingTilesBoard Dimension + Undo Number Activity
 */
public class ChooseDimensionsSlidingActivity extends AppCompatActivity {
    private EditText dimensionInput, undoInput;
    private Button submitInput;
    private TextView dimensionInstructions, undoInstructions;
    /**
     * the Uri of the image of the board, that the user requests with intent.
     */
    private Uri tileImage;

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

        String dimensionSize = dimensionInput.getText().toString();
        String undoMaxText = undoInput.getText().toString();
        Map<Boolean, String> validator= InputValidator.controllerChoosingInput(dimensionSize, undoMaxText);
        if(validator.containsKey(true)){
            int dimension=Integer.valueOf(dimensionSize);
            int undoMax = Integer.valueOf(undoMaxText);
            Intent tmp = new Intent(this, SlidingGameActivity.class);
            SlidingBoardManager slidingBoardManager = new SlidingBoardManager(dimension,undoMax);
            if (tileImage != null) {
                slidingBoardManager.getBoard().setPicturePath(tileImage.toString());
            }
            SaveAndLoadBoardManager.saveToFile(this, SlidingTilesStartingActivity.SAVE_FILENAME, slidingBoardManager);
            startActivity(tmp);
        }
        else{
            Toast.makeText(this,validator.get(false),Toast.LENGTH_SHORT).show();
        }

    }
}
