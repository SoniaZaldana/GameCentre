package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChooseDimensionsActivity extends AppCompatActivity {
    EditText inputText;
    Button submitInput;
    TextView instructions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_complexity);
        inputText = (EditText)findViewById(R.id.EditText);
        submitInput = (Button) findViewById(R.id.submitDimension);
        instructions = (TextView) findViewById(R.id.instructions);

    }
    public void submitInput(View view) {

        String text = inputText.getText().toString();
        try {
            Integer dimension = Integer.parseInt(text);
            if(dimension>19){
                instructions.setText("Please enter a valid number less than 20!");
            }
            else if(dimension==1){
                instructions.setText("Too easy :) Try something harder!");

            }
            else{
                Intent tmp = new Intent(this, GameActivity.class);
                BoardManager boardManager = new BoardManager(dimension);
                SaveAndLoad.saveToFile(this, StartingActivity.SAVE_FILENAME, boardManager);
                startActivity(tmp);
            }
            }
        catch(NumberFormatException e){
            instructions.setText("Please enter a valid number!");
        }
    }
}