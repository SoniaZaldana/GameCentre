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

public class Loginactivity extends AppCompatActivity implements View.OnClickListener{
    Button bLogin, register;
    EditText eUsername, ePassword;
    SharedPreferences accounts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accounts = getApplicationContext().getSharedPreferences("Users", Context.MODE_PRIVATE);
        eUsername =  findViewById(R.id.IDPassword2);
        ePassword =  findViewById(R.id.IDPassword);
        bLogin = findViewById(R.id.login);
        register = findViewById(R.id.registerbutton);
        register.setOnClickListener(this);
        bLogin.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                String Username = eUsername.getText().toString();
                String Password = ePassword.getText().toString();
                String AuthenticationPassword = accounts.getString(Username,
                        "Account does not exists");
                Toast.makeText(this , AuthenticationPassword, Toast.LENGTH_SHORT).show();
                if (AuthenticationPassword.equals(Password)) {
                    startActivity(new Intent(String.valueOf(Gamelauncheractivity.class))
                            .putExtra("Username", Username));
                }
//                Toast.makeText(this , "Invalid Password", Toast.LENGTH_SHORT).show();
                break;
            case R.id.registerbutton:
                startActivity(new Intent(this ,RegisteractivityActivity.class));
                break;
        }

    }
}
