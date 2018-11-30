package fall2018.csc2017.GameCentre;

import android.content.Context;
import android.content.SharedPreferences;

public class AccountManager {
    private SharedPreferences accounts;
    Context context;

    public AccountManager(Context context) {
        accounts = context.getSharedPreferences("users", Context.MODE_PRIVATE);
        this.context = context;
    }
    public String getAccountPassword(String username) {
        return accounts.getString(username, "Account does not exists");
    }

    public boolean doesAccountExist(String username) {
        return accounts.contains(username);
    }

    public void createAccount(String username, String password) {
        SharedPreferenceManager.setSharedValue(context, "users", username, password);
    }
}
