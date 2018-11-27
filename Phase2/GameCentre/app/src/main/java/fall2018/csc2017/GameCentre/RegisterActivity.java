package fall2018.csc2017.GameCentre;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * The Activity to Register a new user
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button registerButton;
    private EditText usernameText, passwordText, reEnteredPasswordText;
    /**
     *  The shared preference which stores  users.
     */
    private SharedPreferences users;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        users = getApplicationContext().getSharedPreferences("users", Context.MODE_PRIVATE);
        setUpVisuals();
    }

    /**
     * Actions required to set up labels and buttons
     */
    private void setUpVisuals() {
        usernameText = findViewById(R.id.username);
        passwordText = findViewById(R.id.IDPassword);
        reEnteredPasswordText = findViewById(R.id.IDPassword2);
        registerButton = findViewById(R.id.registerbutton);
        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registerbutton:
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                String reEnteredPassword = reEnteredPasswordText.getText().toString();
                if (users.contains(username)) {
                    wrongSound();
                    Toast.makeText(this, String.format("Account with the name \"%s\" " +
                                    "already exists, please try another username", username),
                            Toast.LENGTH_SHORT).show();
                } else if (isValidLength(username)) {
                    wrongSound();
                    Toast.makeText(this, "Your username should contain at least 5 " +
                                    "characters",
                            Toast.LENGTH_SHORT).show();
                } else if (isValidLength(password)) {
                    wrongSound();
                    Toast.makeText(this, "Your password should contain at least 5 " +
                                    "characters",
                            Toast.LENGTH_SHORT).show();
                } else if (!password.equals(reEnteredPassword)) {
                    wrongSound();
                    Toast.makeText(this, "Your passwords do not match",
                            Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor Accounts = users.edit();
                    Accounts.putString(username, password);
                    Accounts.apply();
                    startActivity(new Intent(this,
                            GameLauncherActivity.class));
                    SharedPreferenceManager.setSharedValue(this, "sharedUser",
                            "thisUser", username);
                    finish();
                }
                break;
        }

    }

    private void wrongSound() {
        final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.incorrect);
        mp.start();
    }

    /**
     * Returns if an input is of valid length
     *
     * @param input - the username input
     * @return
     */
    private boolean isValidLength(String input) {
        return input.length() <= 4;
    }
}

