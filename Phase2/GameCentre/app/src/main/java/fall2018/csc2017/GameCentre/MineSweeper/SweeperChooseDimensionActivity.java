/**
 * Excluded from tests because it is a view class.
 */
package fall2018.csc2017.GameCentre.MineSweeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import fall2018.csc2017.GameCentre.R;

public class SweeperChooseDimensionActivity extends AppCompatActivity implements View.OnClickListener {
    Button startButton;
    ArrayAdapter dimensionAdapter, complexityAdapter;
    String complexityIndicator = "", dimensionIndicator = "";
    int dimension, complexity;

    @Override
    public void onCreate(Bundle x) {
        super.onCreate(x);
        setContentView(R.layout.choose_complexity_minesweeper);
        final Spinner dimensionSpinner =  findViewById(R.id.spinner);
        final Spinner complexitySpinner = findViewById(R.id.spinner2);
        dimensionAdapter = ArrayAdapter.createFromResource(this,
                R.array.dimensionChoice, android.R.layout.simple_spinner_item);
        complexityAdapter = ArrayAdapter.createFromResource(this,
                R.array.complexityChoice, android.R.layout.simple_spinner_item);
        dimensionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                dimensionIndicator = adapterView.getItemAtPosition(position).toString();
                dimension = getDimension(dimensionIndicator);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        complexitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                complexityIndicator = adapterView.getItemAtPosition(position).toString();
                complexity = getComplexity(complexityIndicator);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        dimensionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        complexityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        complexitySpinner.setAdapter(complexityAdapter);
        dimensionSpinner.setAdapter(dimensionAdapter);
        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startButton:
                if (dimensionIndicator.equals("")) {
                    Toast.makeText(this, "Please select dimension"
                            , Toast.LENGTH_SHORT).show();
                } else if (complexityIndicator.equals("")) {
                    Toast.makeText(this, "Please select complexity",
                            Toast.LENGTH_LONG).show();
                }
                Intent startGame = new Intent(this, MineSweeperActivity.class);
                startGame.putExtra("Dimension", dimension);
                startGame.putExtra("Complexity", complexity);
                startActivity(startGame);
        }

    }

    private int getComplexity(String complexityIndicator) {
        switch (complexityIndicator) {
            case "Easy":
                return 15;
            case "Normal":
                return 20;
            default:
                return 30;
        }
    }

    private int getDimension(String dimensionIndicator) {
        switch (dimensionIndicator) {
            case "Small":
                return 8;
            case "Medium":
                return 13;
            default:
                return 18;
        }
    }
}
