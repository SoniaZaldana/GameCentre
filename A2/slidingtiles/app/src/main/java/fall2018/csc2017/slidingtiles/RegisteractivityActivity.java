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
    Button registerButton, backToLogIn;
    EditText usernameText, passwordText, reEnteredPasswordText;
    SharedPreferences users;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        users =  getApplicationContext().getSharedPreferences("users", Context.MODE_PRIVATE);
        usernameText = findViewById(R.id.username);
        passwordText = findViewById(R.id.IDPassword);
        reEnteredPasswordText = findViewById(R.id.IDPassword2);
        registerButton = findViewById(R.id.registerbutton);
        backToLogIn = findViewById(R.id.login);
        registerButton.setOnClickListener(this);
        backToLogIn.setOnClickListener(this);


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                startActivity(new Intent(this, Loginactivity.class));
                break;
            case R.id.registerbutton:
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                String reEnteredPassword = reEnteredPasswordText.getText().toString();
                if (users.contains(username)) {
                    Toast.makeText(this, String.format("Account with the name \"%s\" " +
                                    "already exists, please try another username", username),
                            Toast.LENGTH_SHORT).show();
                } else if (isValidUsername(username)) {
                    Toast.makeText(this, "Your username should contain at least 5 characters",
                            Toast.LENGTH_SHORT).show();
                } else if (isValid(password)) {
                    Toast.makeText(this, "Your password should contain at least 5 characters",
                            Toast.LENGTH_SHORT).show();
                } else if (!password.equals(reEnteredPassword)) {
                    Toast.makeText(this, "Your passwords do not match",
                            Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor Accounts = users.edit();
                    Accounts.putString(username, password);
                    Accounts.apply();
                    startActivity(new Intent(this,
                            Gamelauncheractivity.class).putExtra("Username", username));
                }
                break;
        }

    }

    private boolean isValidUsername(String username) {
        return username.length() <= 4;
    }

    private boolean isValid(String password) {
        return password.length() <= 4;


    }
}

