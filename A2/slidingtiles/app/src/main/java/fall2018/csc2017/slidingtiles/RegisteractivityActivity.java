package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisteractivityActivity extends AppCompatActivity implements View.OnClickListener {
    Button RegisterButton, BackToLogIn;
    EditText eUsername, ePassword, reEnteredPassword;
    SharedPreferences Users;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Users =  getApplicationContext().getSharedPreferences("Users", Context.MODE_PRIVATE);
        eUsername = findViewById(R.id.IDPassword2);
        ePassword = findViewById(R.id.IDPassword);
        reEnteredPassword = findViewById(R.id.IDPassword2);
        RegisterButton = findViewById(R.id.registerbutton);
        BackToLogIn = findViewById(R.id.login);
        RegisterButton.setOnClickListener(this);
        BackToLogIn.setOnClickListener(this);


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                startActivity(new Intent(this, Loginactivity.class));
                break;
            case R.id.registerbutton:
                String Username = eUsername.getText().toString();
                String Password = ePassword.getText().toString();
                String reEnteredPassword2 = reEnteredPassword.getText().toString();
                if (isValid(Password) && Password.equals(reEnteredPassword2)) {
                    SharedPreferences.Editor Accounts = Users.edit();
                    Accounts.putString(Username, Password);
                    Accounts.apply();
                    startActivity(new Intent(this, Gamelauncheractivity.class));
                }
                else {
                    Toast.makeText(this, "Passwords does not match",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    private boolean isValid(String ePassword) {
//        if(ePassword.length() <= 25) return false;
        return true;

    }
}

