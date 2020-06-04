package Utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    SharedPreferences preferences;
    Context context;
    public static String prefname = "hrfuopref";

    public SharedPrefManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(prefname, Context.MODE_PRIVATE);
    }

    public void saveString(String name, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(name,value);
        editor.apply();
    }
    public String readString(String keyName, String defaultValue) {
        defaultValue = preferences.getString(keyName, defaultValue);
        return defaultValue;
    }
}
