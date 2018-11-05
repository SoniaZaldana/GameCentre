package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChooseDimensionsActivity extends AppCompatActivity {
    EditText dimensionInput;
    Button submitInput;
    TextView dimensionInstructions;
    EditText undoInput;
    TextView undoInstructions;
    Uri tileImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_complexity);
        dimensionInput = (EditText)findViewById(R.id.EditDimension);
        submitInput = (Button) findViewById(R.id.submitEverything);
        dimensionInstructions = (TextView) findViewById(R.id.dimensionInstructions);
        undoInput = (EditText) findViewById(R.id.editUndo);
        undoInstructions = (TextView) findViewById(R.id.undoInstructions);
        String tileImgAsStr = getIntent().getStringExtra("TileImage");
        if(tileImgAsStr != null){
            tileImage = Uri.parse(tileImgAsStr);
        }



    }
    public void submitInput(View view) {

        String text = dimensionInput.getText().toString();
        String undoMaxText = undoInput.getText().toString();
        try {
            Integer dimension = Integer.parseInt(text);
            if(dimension>19){
                dimensionInstructions.setText("Please enter a valid number less than 20!");
            }
            else if(dimension==1){
                dimensionInstructions.setText("Too easy :) Try something harder!");

            }
            else{
                Intent tmp = new Intent(this, GameActivity.class);

                try {
                    Integer undoMax = Integer.parseInt(undoMaxText);

                    if (undoMax <= 0) {
                        undoInstructions.setText("Please enter a number greater than 0");
                    }
                    else {

                        BoardManager boardManager = new BoardManager(dimension, undoMax);
                        if(tileImage!=null){
                            boardManager.getBoard().setPicturePath(tileImage.toString());
                        }
                        SaveAndLoad.saveToFile(this, StartingActivity.SAVE_FILENAME, boardManager);
                        startActivity(tmp);
                    }
                } catch(NumberFormatException e){
                    undoInstructions.setText("Please enter a valid number!");
                }

            }
            }
        catch(NumberFormatException e){
            dimensionInstructions.setText("Please enter a valid number!");
        }
    }
}
