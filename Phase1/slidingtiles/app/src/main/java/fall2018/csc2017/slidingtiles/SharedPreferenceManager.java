package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.SharedPreferences;
import static android.content.Context.MODE_PRIVATE;

class SharedPreferenceManager {

    /**
     * Creates a shared preference file (if it doesn't exist already) and adds a key-value pair,
     * otherwise it just adds the key-value pair to the existing shared preference file.
     * @param c - the activity's context
     * @param spName - shared preference's file name
     * @param key - the key for the value to be stored in shared preference file
     * @param value - the value to match the key in the shared preference file
     */
    static void setSharedValue(Context c, String spName, String key, String value) {
        SharedPreferences sp = c.getApplicationContext().getSharedPreferences(spName, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Returns the value corresponding to a certain key, in the shared preference file spName,
     * corresponding to that given context c.
     * @param c - the activitu's context
     * @param spName - shared preference's file name
     * @param key - the key for the value we are looking for
     * @return the value matching the key in the shared preference's file name
     */
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
