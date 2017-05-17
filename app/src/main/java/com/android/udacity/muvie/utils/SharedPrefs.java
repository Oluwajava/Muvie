package com.android.udacity.muvie.utils;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by Mayokun on 5/16/2017.
 */

public class SharedPrefs {

    SharedPreferences sharedPreferences;

    @Inject
    public SharedPrefs(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, null);
    }
}
