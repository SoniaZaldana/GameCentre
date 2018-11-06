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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    Button loginButton, registerButton;
    EditText usernameText, passwordText;
    SharedPreferences accounts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accounts = getApplicationContext().getSharedPreferences("users", Context.MODE_PRIVATE);
        setUpVisuals();

    }

    /**
     * Sets up the visuals for the activities such as buttons and labels
     */
    private void setUpVisuals() {
        usernameText =  findViewById(R.id.username);
        passwordText =  findViewById(R.id.IDPassword);
        loginButton = findViewById(R.id.login);
        registerButton = findViewById(R.id.registerbutton);
        registerButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                String authenticationPassword = accounts.getString(username,
                        "Account does not exists");
                if (!accounts.contains(username)) {
                    Toast.makeText(this , String.format("Account with name " +
                            "\"%s\" does not exist", username), Toast.LENGTH_SHORT).show();
                } else if (!authenticationPassword.equals(password)) {
                    Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(this, GameLauncherActivity.class));
                    SharedPreferenceManager.setSharedValue(this, "sharedUser", "thisUser", username);
                    passwordText.setText("");
                }
                break;
            case R.id.registerbutton:
                startActivity(new Intent(this ,RegisterActivity.class));
                break;
        }

    }
}
