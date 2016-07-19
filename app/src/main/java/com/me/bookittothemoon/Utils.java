package com.me.bookittothemoon;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ctewani on 4/24/16.
 */

public class Utils {
    public static void putStringInPreferences(Context context, String value, String key, String pKey) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(pKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getStringFromPreferences(Context context,String defaultValue, String key, String pKey) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(pKey, Context.MODE_PRIVATE);
        String temp = sharedPreferences.getString(key, defaultValue);
        return temp;
    }
}
