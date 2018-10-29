package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisteractivityActivity extends AppCompatActivity implements View.OnClickListener {
    Button RegisterButton, BackToLogIn;
    EditText eUsername, ePassword, reEnteredPassword;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        eUsername = findViewById(R.id.Username);
        ePassword = findViewById(R.id.IDPassword);
        reEnteredPassword = findViewById(R.id.IDPassword2);
        RegisterButton = findViewById(R.id.RegisterButton);
        BackToLogIn = findViewById(R.id.Login);
        RegisterButton.setOnClickListener(this);
        BackToLogIn.setOnClickListener(this);


    }
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Login:
                startActivity(new Intent(this, loginactivity.class));
                break;
            case R.id.RegisterButton:
                String Username = eUsername.getText().toString();
                String Password = ePassword.getText().toString();
                String reEnteredPassword2 = reEnteredPassword.getText().toString();
                if (isValid(Password) && ePassword.equals(reEnteredPassword2)) {

                    startActivity(new Intent(String.valueOf(gamelauncheractivity.class)));
                }
                break;
        }

    }

    private boolean isValid(String ePassword) {
        return true;

    }
}

