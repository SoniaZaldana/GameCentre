package fall2018.csc2017.slidingtiles;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class loginactivity extends AppCompatActivity implements View.OnClickListener{
    Button bLogin, register;
    EditText eUsername, ePassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        eUsername =  findViewById(R.id.Username);
        ePassword =  findViewById(R.id.IDPassword);
        bLogin = findViewById(R.id.Login);
        register = findViewById(R.id.RegisterButton);
        register.setOnClickListener(this);
        bLogin.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Login:
                String Username = eUsername.getText().toString();
                String Password = ePassword.getText().toString();
//                Check whether account exists
                startActivity(new Intent(this, gamelauncheractivity.class ));
                break;
            case R.id.RegisterButton:
                startActivity(new Intent(String.valueOf(RegisteractivityActivity.class)));
                break;
        }

    }
}
