package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.SharedPreferences;
import static android.content.Context.MODE_PRIVATE;

class SharedPreferenceManager {

    static void setSharedValue(Context c, String spName, String key, String value) {
        SharedPreferences sp = c.getApplicationContext().getSharedPreferences(spName, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    static String getSharedValue(Context c, String spName, String key) {
        SharedPreferences sp = c.getApplicationContext().getSharedPreferences(spName, MODE_PRIVATE);
        return sp.getString(key, "User");
    }

    static void deleteUser(Context c, String spName, String key) {
        SharedPreferences sp = c.getApplicationContext().getSharedPreferences(spName, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();

    }
}
