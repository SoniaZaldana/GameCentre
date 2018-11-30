package fall2018.csc2017.GameCentre;

import android.content.Context;
import android.content.SharedPreferences;

class AccountManager {
    private SharedPreferences accounts;
    Context context;

    AccountManager(Context context) {
        accounts = context.getSharedPreferences("users", Context.MODE_PRIVATE);
        this.context = context;
    }
    String getAccountPassword(String username) {
        return accounts.getString(username, "Account does not exists");
    }

    boolean doesAccountExist(String username) {
        return accounts.contains(username);
    }

    void createAccount(String username, String password) {
        SharedPreferenceManager.setSharedValue(context, "users", username, password);
    }
}
