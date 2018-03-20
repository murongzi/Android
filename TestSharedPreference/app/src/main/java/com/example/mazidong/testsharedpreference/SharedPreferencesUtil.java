package com.example.mazidong.testsharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by mazidong on 2018/3/20.
 */

public class SharedPreferencesUtil {
    private final static String sp_file_name = "data";

    public static void sp_first_write(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(SharedPreferencesUtil.sp_file_name, Context.MODE_PRIVATE).edit();
        editor.putString("name", "Tom");
        editor.putInt("age", 28);
        editor.putBoolean("married", false);
        editor.apply();
    }

    public static void sp_second_write(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean("isRemember", true);
        editor.putString("account", "helen");
        editor.putString("password", "password");
        editor.apply();
    }

    public static String sp_read (Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SharedPreferencesUtil.sp_file_name, Context.MODE_PRIVATE);

        String name = preferences.getString("name", "");
        int age = preferences.getInt("age", 0);
        boolean married = preferences.getBoolean("married", false);

        return name;
    }
}
