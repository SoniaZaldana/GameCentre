package fall2018.csc2017.GameCentre.Simon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import fall2018.csc2017.GameCentre.R;

public class SavePopup extends Activity {
    private Button saveFile;
    private EditText file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_popup);
        saveFile = findViewById(R.id.enterFile);
        file = findViewById(R.id.fileName);
        saveFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = file.getText().toString();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("fileName",fileName);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

//        int width = dm.widthPixels;
//        int height = dm.heightPixels;
//
//        getWindowManager().setLayout((int) (width*.6), (int) (height*.5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

    }
}
